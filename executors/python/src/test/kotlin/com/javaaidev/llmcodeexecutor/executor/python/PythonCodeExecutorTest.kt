package com.javaaidev.llmcodeexecutor.executor.python

import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters
import com.javaaidev.llmcodeexecutor.executor.model.OutputFileCollectionConfig
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PythonCodeExecutorTest {
    @Test
    fun basic() {
        val result = PythonCodeExecutor().execute(
            ExecuteCodeParameters(
                """
        print("Hello")
    """.trimIndent()
            )
        )
        assertEquals("Hello", result.output.trim())
    }

    @Test
    fun collectFiles() {
        val result = PythonCodeExecutor().execute(
            ExecuteCodeParameters(
                """
        import numpy as np
        import matplotlib.pyplot as plt
        x = np.random.randint(0, 100, 100)
        y = np.random.randint(0, 100, 100)
        plt.scatter(x, y)
        plt.savefig('scatter.png')
        print('Scatter plot saved to scatter.png')
        
    """.trimIndent(),
                null,
                OutputFileCollectionConfig(
                    false,
                    true,
                    "./target",
                    "*.png"
                )
            )
        )
        assertEquals(1, result.copiedFiles?.size)
    }
}