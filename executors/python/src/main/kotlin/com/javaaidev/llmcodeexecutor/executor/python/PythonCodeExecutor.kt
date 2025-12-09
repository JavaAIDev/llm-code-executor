package com.javaaidev.llmcodeexecutor.executor.python

import com.javaaidev.llmcodeexecutor.executor.core.*
import java.nio.file.Files
import java.time.Duration

class PythonCodeExecutor(private val config: CodeExecutionConfig? = null) : CodeExecutor {

    override fun execute(
        request: ExecuteCodeParameters
    ): ExecuteCodeResult {
        val codeDir = Files.createTempDirectory("code_executor")
        val codeFile = (request.codeFileName ?: "").ifBlank { "app.py" }
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
        return codeExecutor.execute(request.withDefaultIncludedFilePattern("!${codeFile}"))
    }
}
