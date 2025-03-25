package com.javaaidev.llmcodeexecutor.containerimagegenerator

import com.javaaidev.llmcodeexecutor.containerimagegenerator.python.PythonContainerImageGeneratorCommand
import picocli.CommandLine
import picocli.CommandLine.Command
import java.io.File
import kotlin.system.exitProcess

@Command
class ReusableOptions {
    @CommandLine.Parameters(index = "0")
    lateinit var inputProfile: File

    @CommandLine.Option(
        names = ["--output"],
        defaultValue = "./output",
        description = ["Output directory"]
    )
    lateinit var outputDir: File
}

@Command(
    name = "llm-code-executor",
    mixinStandardHelpOptions = true,
    version = ["0.1.0"],
    description = ["Generate container image for LLM code execution"],
    scope = CommandLine.ScopeType.INHERIT,
    subcommands = [
        PythonContainerImageGeneratorCommand::class,
    ],
)
class ContainerImageGeneratorCli

fun main(args: Array<String>) {
    exitProcess(CommandLine(ContainerImageGeneratorCli()).execute(*args))
}