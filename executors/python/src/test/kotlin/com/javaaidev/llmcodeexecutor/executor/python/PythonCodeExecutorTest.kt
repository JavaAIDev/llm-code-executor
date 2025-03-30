package com.javaaidev.llmcodeexecutor.executor.python

import com.javaaidev.llmcodeexecutor.core.CodeExecutionRequest
import com.javaaidev.llmcodeexecutor.core.Manual
import com.javaaidev.llmcodeexecutor.core.OutputFileCollectionConfig
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Manual
class PythonCodeExecutorTest {
    @Test
    fun basic() {
        val result = PythonCodeExecutor.execute(
            CodeExecutionRequest(
                """
        print("Hello")
    """.trimIndent()
            )
        )
        assertEquals("Hello", result.output.trim())
    }

    @Test
    fun collectFiles() {
        val result = PythonCodeExecutor.execute(
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
        assertEquals(1, result.copiedFiles?.size)
    }
}