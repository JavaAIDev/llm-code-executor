package com.javaaidev.llmcodeexecutor.executor.python

import com.javaaidev.llmcodeexecutor.core.*
import java.nio.file.Files
import java.time.Duration

data class PythonCodeExecutorConfig(
    val containerImage: String? = null,
)

class PythonCodeExecutor(private val config: PythonCodeExecutorConfig? = null) {

    fun execute(
        request: CodeExecutionRequest
    ): CodeExecutionResponse {
        val codeDir = Files.createTempDirectory("code_executor")
        val codeFile = "app.py"
        Files.writeString(codeDir.resolve(codeFile), request.code)
        val codeExecutor = LLMCodeExecutor(
            CodeExecutorConfig(
                config?.containerImage ?: "ghcr.io/javaaidev/llm-code-executor-python:base-3.12",
                listOf("uv", "run", codeFile),
                listOf("/app/.venv"),
                listOf(
                    VolumeBind(
                        codeDir.toAbsolutePath().normalize().toString(),
                        "/app"
                    )
                ),
                "/app",
                Duration.ofMinutes(3),
                "/app",
            )
        )
        return codeExecutor.execute(
            request.copy(
                outputFileCollectionConfig = request.outputFileCollectionConfig?.copy(
                    includedFilePattern = request.outputFileCollectionConfig?.includedFilePattern
                        ?: "!${codeFile}"
                )
            )
        )
    }
}
