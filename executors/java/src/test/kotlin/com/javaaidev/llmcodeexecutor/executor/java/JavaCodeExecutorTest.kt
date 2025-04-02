package com.javaaidev.llmcodeexecutor.executor.java

import com.javaaidev.llmcodeexecutor.core.CodeExecutionRequest
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

class JavaCodeExecutorTest {
    @Test
    @Ignore
    fun basic() {
        val result = JavaCodeExecutor.execute(
            CodeExecutionRequest(
                """
        public class Main {
            public static void main(String[] args) {
                System.out.println("hello");
            }
        }
    """.trimIndent()
            )
        )
        kotlin.test.assertEquals("Hello", result.output.trim())
    }
}