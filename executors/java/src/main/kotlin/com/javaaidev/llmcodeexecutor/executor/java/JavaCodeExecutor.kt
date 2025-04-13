package com.javaaidev.llmcodeexecutor.executor.java

import com.javaaidev.llmcodeexecutor.executor.core.CodeExecutorConfig
import com.javaaidev.llmcodeexecutor.executor.core.LLMCodeExecutor
import com.javaaidev.llmcodeexecutor.executor.core.VolumeBind
import com.javaaidev.llmcodeexecutor.executor.core.withDefaultIncludedFilePattern
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeConfiguration
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeReturnType
import java.nio.file.Files
import java.time.Duration

class JavaCodeExecutor(private val config: ExecuteCodeConfiguration? = null) {

    fun execute(
        request: ExecuteCodeParameters
    ): ExecuteCodeReturnType {
        val codeDir = Files.createTempDirectory("code_executor")
        val codeFile = (request.codeFileName ?: "").ifBlank { "Main.java" }
        val mainClass = codeFile.removeSuffix(".java")
        Files.writeString(codeDir.resolve(codeFile), request.code)
        val codeExecutor = LLMCodeExecutor(
            CodeExecutorConfig(
                config?.containerImage ?: "ghcr.io/javaaidev/llm-code-executor-java:base-21",
                listOf(
                    "mvn",
                    "-q",
                    "compile",
                    "-Dexec.mainClass=${mainClass}",
                    "exec:java"
                ),
                listOf(),
                listOf(
                    VolumeBind(
                        codeDir.toAbsolutePath().normalize().toString(),
                        "/app/src/main/java"
                    )
                ),
                "/app",
                Duration.ofMinutes(3),
                "/app",
            )
        )
        request.withDefaultIncludedFilePattern("!${codeFile}")
        return codeExecutor.execute(request)
    }
}