package com.javaaidev.llmcodeexecutor.executor.java

import com.javaaidev.llmcodeexecutor.executor.core.ExecuteCodeParameters
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

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
        assertEquals("Hello", result.output?.trim())
    }

    @Test
    fun codeFileName() {
        val result = JavaCodeExecutor().execute(
            ExecuteCodeParameters(
                """
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        
        public class CurrentTime {
            public static void main(String[] args) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("Current time: " + now.format(formatter));
            }
        }
    """.trimIndent(),
                "CurrentTime.java",
                null
            )
        )
        assertNotNull(result.output)
        assertEquals("", result.error)
    }
}