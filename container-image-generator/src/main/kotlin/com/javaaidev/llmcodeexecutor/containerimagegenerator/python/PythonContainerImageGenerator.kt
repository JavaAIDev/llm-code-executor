package com.javaaidev.llmcodeexecutor.containerimagegenerator.python

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.jknack.handlebars.EscapingStrategy
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.javaaidev.llmcodeexecutor.containerimagegenerator.ReusableOptions
import org.slf4j.LoggerFactory
import picocli.CommandLine
import picocli.CommandLine.Command
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.Callable
import java.util.function.Supplier
import kotlin.io.path.exists

data class GeneratedFile(
    val templateName: String,
    val dir: Path,
    val overwrite: Boolean = true,
    val fileNameSupplier: Supplier<String>,
)

object PythonContainerImageGenerator {
    private val handlebars: Handlebars
    private val logger = LoggerFactory.getLogger(PythonContainerImageGenerator::class.java)

    init {
        val loader = ClassPathTemplateLoader()
        loader.prefix = "/templates/python"
        handlebars = Handlebars(loader).with(EscapingStrategy.NOOP)
    }

    fun generate(profile: PythonProfile, outputDir: Path) {
        val appDir = outputDir.resolve("app")
        Files.createDirectories(appDir)
        val files = listOf(
            GeneratedFile("pyproject.toml", appDir) { "pyproject.toml" },
            GeneratedFile("Dockerfile", outputDir) { "Dockerfile" },
        )

        files.forEach { file ->
            val outputFile = file.dir.resolve(file.fileNameSupplier.get())
            if (outputFile.exists() && !file.overwrite) {
                logger.info("Skip generation of file {}", outputFile)
                return@forEach
            }
            val template = handlebars.compile(file.templateName)
            template.apply(profile).run {
                logger.info("Created file {}", outputFile.toAbsolutePath().normalize())
                Files.writeString(outputFile, this)
            }
        }
    }
}

@Command(name = "python", description = ["Generate Python container image"])
class PythonContainerImageGeneratorCommand : Callable<Int> {
    @CommandLine.Mixin
    lateinit var options: ReusableOptions

    private val objectMapper = ObjectMapper().registerModules(KotlinModule.Builder().build())

    override fun call(): Int {
        val profile = objectMapper.readValue(options.inputProfile, PythonProfile::class.java)
        PythonContainerImageGenerator.generate(profile, options.outputDir.toPath())
        return 0
    }
}
