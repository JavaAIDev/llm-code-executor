package com.javaaidev.llmcodeexecutor.executor.python

import com.javaaidev.llmcodeexecutor.core.*
import java.nio.file.Files

object PythonCodeExecutor {

    fun execute(request: CodeExecutionRequest): CodeExecutionResponse {
        val codeDir = Files.createTempDirectory("code_executor")
        Files.writeString(codeDir.resolve("app.py"), request.code)
        val codeExecutor = LLMCodeExecutor(
            CodeExecutorConfig(
                "python-executor",
                listOf("uv", "run", "app.py"),
                listOf("/app/.venv"),
                listOf(
                    VolumeBind(
                        codeDir.toAbsolutePath().normalize().toString(),
                        "/app"
                    )
                ),
                "/app",
                containerOutputDirectory = "/app",
            )
        )
        return codeExecutor.execute(request)
    }
}

fun main() {
    println(
        PythonCodeExecutor.execute(
            CodeExecutionRequest(
                """
        import numpy as np
        import matplotlib.pyplot as plt
        x = np.random.randint(0, 100, 100)
        y = np.random.randint(0, 100, 100)
        plt.scatter(x, y)
        plt.savefig('scatter.png')
        print('Scatter plot saved to scatter.png')
        
    """.trimIndent(),
                OutputFileCollectionConfig(
                    copyFiles = true,
                    copiedFilesPath = "./target",
                    includedFilePattern = "*.png"
                )
            )
        )
    )
}