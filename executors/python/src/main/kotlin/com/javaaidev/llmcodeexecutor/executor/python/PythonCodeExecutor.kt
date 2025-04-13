package com.javaaidev.llmcodeexecutor.executor.python

import com.javaaidev.llmcodeexecutor.executor.core.CodeExecutorConfig
import com.javaaidev.llmcodeexecutor.executor.core.LLMCodeExecutor
import com.javaaidev.llmcodeexecutor.executor.core.VolumeBind
import com.javaaidev.llmcodeexecutor.executor.core.withDefaultIncludedFilePattern
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeConfiguration
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters
import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeReturnType
import java.nio.file.Files
import java.time.Duration

class PythonCodeExecutor(private val config: ExecuteCodeConfiguration? = null) {

    fun execute(
        request: ExecuteCodeParameters
    ): ExecuteCodeReturnType {
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
        request.withDefaultIncludedFilePattern("!${codeFile}")
        return codeExecutor.execute(request)
    }
}
