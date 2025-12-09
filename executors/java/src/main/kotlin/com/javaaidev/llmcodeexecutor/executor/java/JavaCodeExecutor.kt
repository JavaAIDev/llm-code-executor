package com.javaaidev.llmcodeexecutor.executor.java

import com.javaaidev.llmcodeexecutor.executor.core.*
import java.nio.file.Files
import java.time.Duration

class JavaCodeExecutor(private val config: CodeExecutionConfig? = null) : CodeExecutor {

    override fun execute(
        request: ExecuteCodeParameters
    ): ExecuteCodeResult {
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
        return codeExecutor.execute(request.withDefaultIncludedFilePattern("!${codeFile}"))
    }
}