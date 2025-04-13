package com.javaaidev.llmcodeexecutor.executor.java

import com.javaaidev.llmcodeexecutor.executor.model.ExecuteCodeParameters
import org.junit.jupiter.api.Test

class JavaCodeExecutorTest {
    @Test
    fun basic() {
        val result = JavaCodeExecutor().execute(
            ExecuteCodeParameters(
                """
        public class Main {
            public static void main(String[] args) {
                System.out.println("Hello");
            }
        }
    """.trimIndent()
            )
        )
        kotlin.test.assertEquals("Hello", result.output.trim())
    }
}