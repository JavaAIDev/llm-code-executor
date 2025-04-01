package com.javaaidev.llmcodeexecutor.containerimagegenerator.java

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.javaaidev.llmcodeexecutor.containerimagegenerator.GeneratedFile
import com.javaaidev.llmcodeexecutor.containerimagegenerator.ReusableOptions
import com.javaaidev.llmcodeexecutor.containerimagegenerator.TemplateHelper
import org.slf4j.LoggerFactory
import picocli.CommandLine
import picocli.CommandLine.Command
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.Callable

object JavaContainerImageGenerator {
    private val logger = LoggerFactory.getLogger(JavaContainerImageGenerator::class.java)

    fun generate(profile: JavaProfile, outputDir: Path) {
        logger.info("Generate Java container image")
        val appDir = outputDir.resolve("app")
        Files.createDirectories(appDir)
        val files = listOf(
            GeneratedFile("pom.xml", appDir),
            GeneratedFile("Main.java", appDir),
            GeneratedFile("Dockerfile", outputDir),
        )

        TemplateHelper.writeFiles("/templates/java", files, profile)
    }
}

@Command(name = "java", description = ["Generate Java container image"])
class JavaContainerImageGeneratorCommand : Callable<Int> {
    @CommandLine.Mixin
    lateinit var options: ReusableOptions

    private val objectMapper = ObjectMapper().registerModules(KotlinModule.Builder().build())

    override fun call(): Int {
        val profile = objectMapper.readValue(options.inputProfile, JavaProfile::class.java)
        JavaContainerImageGenerator.generate(profile, options.outputDir.toPath())
        return 0
    }
}