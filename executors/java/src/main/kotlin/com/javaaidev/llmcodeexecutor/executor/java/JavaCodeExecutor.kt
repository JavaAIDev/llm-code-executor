package com.javaaidev.llmcodeexecutor.executor.java

import com.javaaidev.llmcodeexecutor.core.*
import java.nio.file.Files
import java.time.Duration

data class JavaCodeExecutorConfig(
    val containerImage: String? = null,
)

object JavaCodeExecutor {

    fun execute(
        request: CodeExecutionRequest,
        config: JavaCodeExecutorConfig? = null
    ): CodeExecutionResponse {
        val codeDir = Files.createTempDirectory("code_executor")
        val codeFile = "Main.java"
        Files.writeString(codeDir.resolve(codeFile), request.code)
        val codeExecutor = LLMCodeExecutor(
            CodeExecutorConfig(
                config?.containerImage ?: "ghcr.io/javaaidev/llm-code-executor-java:base-21",
                listOf("mvn", "-q", "compile", "exec:java"),
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

fun main() {
    println(
        JavaCodeExecutor.execute(
            CodeExecutionRequest(
                """
        public class Main {
            public static void main(String[] args) {
                System.out.println("hello");
            }
        }
    """.trimIndent(),
                OutputFileCollectionConfig(
                    copyFiles = true,
                    copiedFilesPath = "./target"
                )
            )
        )
    )
}