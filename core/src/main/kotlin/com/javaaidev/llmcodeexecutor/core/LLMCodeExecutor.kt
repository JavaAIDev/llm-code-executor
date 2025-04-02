package com.javaaidev.llmcodeexecutor.core

import com.github.dockerjava.api.async.ResultCallback
import com.github.dockerjava.api.exception.NotModifiedException
import com.github.dockerjava.api.model.*
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.time.Duration
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.io.path.isRegularFile
import kotlin.io.path.listDirectoryEntries

data class VolumeBind(
    val volumeName: String,
    val destinationVolume: String,
)

data class CodeExecutorConfig(
    val containerImage: String,
    val commands: List<String>? = null,
    val volumes: List<String>? = null,
    val volumeBinds: List<VolumeBind>? = null,
    val workingDirectory: String? = null,
    val executionTimeout: Duration? = null,
    val containerOutputDirectory: String? = null,
)

data class CodeExecutionRequest(
    val code: String,
    val outputFileCollectionConfig: OutputFileCollectionConfig? = null,
)

data class OutputFileCollectionConfig(
    val loadFiles: Boolean? = false,
    val copyFiles: Boolean? = false,
    val copiedFilesPath: String? = null,
    val includedFilePattern: String? = null,
)

data class OutputFileContent(
    val mimeType: String,
    val data: String,
)

data class CopiedFile(
    val path: String,
)

data class CodeExecutionResponse(
    val output: String,
    val error: String? = null,
    val loadedFiles: List<OutputFileContent>? = null,
    val copiedFiles: List<CopiedFile>? = null,
)

/**
 * Execute code for LLM
 */
class LLMCodeExecutor(
    private val config: CodeExecutorConfig,
    customDockerClientConfig: DockerClientConfig? = null
) {
    private val logger = LoggerFactory.getLogger(LLMCodeExecutor::class.java)
    private val dockerClientConfig =
        customDockerClientConfig ?: DefaultDockerClientConfig.createDefaultConfigBuilder().build()
    private val httpClient = ApacheDockerHttpClient.Builder()
        .dockerHost(dockerClientConfig.dockerHost)
        .sslConfig(dockerClientConfig.sslConfig)
        .maxConnections(100)
        .connectionTimeout(Duration.ofSeconds(30))
        .responseTimeout(Duration.ofSeconds(45))
        .build()
    private val dockerClient = DockerClientImpl.getInstance(dockerClientConfig, httpClient)

    fun execute(request: CodeExecutionRequest): CodeExecutionResponse {
        pullImage()

        val cmd = dockerClient.createContainerCmd(config.containerImage)
        config.volumes?.let { volumes ->
            cmd.withVolumes(volumes.map { Volume(it) })
        }
        config.volumeBinds?.let { volumes ->
            cmd.withHostConfig(
                HostConfig.newHostConfig()
                    .withBinds(volumes.map { Bind(it.volumeName, Volume(it.destinationVolume)) })
            )
        }
        config.commands?.let {
            cmd.withCmd(it)
        }
        config.workingDirectory?.let {
            cmd.withWorkingDir(it)
        }
        val containerId = cmd.exec().id
        logger.info("Created container {}", containerId)
        dockerClient.startContainerCmd(containerId).exec()
        logger.info("Started container {}", containerId)
        val outputBuilder = StringBuffer()
        val errorBuilder = StringBuffer()
        val countDownLatch = CountDownLatch(1)
        dockerClient.logContainerCmd(containerId).withStdErr(true).withStdOut(true).withTailAll()
            .withFollowStream(true)
            .exec(object : ResultCallback<Frame> {
                private var closeable: Closeable? = null

                override fun close() {

                }

                override fun onStart(closeable: Closeable?) {
                    this.closeable = closeable
                }

                override fun onError(throwable: Throwable?) {
                    throwable?.let {
                        errorBuilder.append(it.message)
                    }
                }

                override fun onComplete() {
                    closeable?.close()
                    countDownLatch.countDown()
                }

                override fun onNext(frame: Frame?) {
                    frame?.let {
                        val content = String(it.payload)
                        when (it.streamType) {
                            StreamType.STDERR -> errorBuilder.append(content)
                            else -> outputBuilder.append(content)
                        }
                    }

                }

            })

        countDownLatch.await(
            (config.executionTimeout ?: Duration.ofMinutes(1)).toSeconds(),
            TimeUnit.SECONDS
        )
        logger.info("Container finished execution {}", containerId)
        try {
            dockerClient.stopContainerCmd(containerId).exec()
        } catch (e: NotModifiedException) {
            // ignore
        }
        val copiedFiles = mutableListOf<CopiedFile>()
        val loadedFiles = mutableListOf<OutputFileContent>()
        request.outputFileCollectionConfig?.let { collectionConfig ->
            if (config.containerOutputDirectory != null && (collectionConfig.loadFiles == true || collectionConfig.copyFiles == true)) {
                val response = dockerClient.inspectContainerCmd(containerId).exec()
                response.mounts?.find { mount -> mount.destination?.path == config.containerOutputDirectory }?.source?.let { source ->
                    Path.of(source)
                        .listDirectoryEntries(collectionConfig.includedFilePattern ?: "*")
                        .filter { it.isRegularFile() }
                        .forEach { path ->
                            if (collectionConfig.copyFiles == true) {
                                val copiedFilesPath =
                                    Path.of(collectionConfig.copiedFilesPath ?: ".")
                                Files.createDirectories(copiedFilesPath)
                                val targetPath = copiedFilesPath.resolve(path.fileName)
                                Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING)
                                copiedFiles.add(
                                    CopiedFile(
                                        targetPath.toAbsolutePath().normalize().toString()
                                    )
                                )
                            } else {
                                val mimeType = Files.probeContentType(path)
                                if (mimeType.startsWith("text/")) {
                                    loadedFiles.add(
                                        OutputFileContent(
                                            mimeType,
                                            Files.readString(path)
                                        )
                                    )
                                } else {
                                    loadedFiles.add(
                                        OutputFileContent(
                                            mimeType,
                                            Base64.getMimeEncoder()
                                                .encodeToString(Files.readAllBytes(path))
                                        )
                                    )
                                }
                            }
                        }
                }
            }
        }

        dockerClient.removeContainerCmd(containerId).exec()
        logger.info("Container removed {}", containerId)
        return CodeExecutionResponse(
            outputBuilder.toString(),
            errorBuilder.toString(),
            loadedFiles,
            copiedFiles
        )
    }

    private fun pullImage() {
        logger.info("Start pulling image {}", config.containerImage)
        val pullImageCountDownLatch = CountDownLatch(1)
        dockerClient.pullImageCmd(config.containerImage)
            .exec(object : ResultCallback<PullResponseItem> {
                override fun close() {

                }

                override fun onStart(closeable: Closeable?) {

                }

                override fun onError(throwable: Throwable?) {
                    logger.error("Failed to pull image", throwable)
                }

                override fun onComplete() {
                    pullImageCountDownLatch.countDown()
                }

                override fun onNext(`object`: PullResponseItem?) {

                }

            })
        pullImageCountDownLatch.await(1, TimeUnit.MINUTES)
        logger.info("Image pulled successfully")
    }
}