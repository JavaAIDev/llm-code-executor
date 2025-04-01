package com.javaaidev.llmcodeexecutor.containerimagegenerator

import com.github.jknack.handlebars.EscapingStrategy
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Supplier
import kotlin.io.path.exists

data class GeneratedFile(
    val templateName: String,
    val dir: Path,
    val overwrite: Boolean = true,
    val fileNameSupplier: Supplier<String> = Supplier<String> { templateName },
)

object TemplateHelper {
    private val logger = LoggerFactory.getLogger(TemplateHelper::class.java)

    fun writeFiles(templatePrefix: String, files: List<GeneratedFile>, templateContext: Any) {
        val loader = ClassPathTemplateLoader()
        loader.prefix = templatePrefix
        val handlebars = Handlebars(loader).with(EscapingStrategy.NOOP)

        files.forEach { file ->
            val outputFile = file.dir.resolve(file.fileNameSupplier.get())
            if (outputFile.exists() && !file.overwrite) {
                logger.info("Skip generation of file {}", outputFile)
                return@forEach
            }
            val template = handlebars.compile(file.templateName)
            template.apply(templateContext).run {
                logger.info("Created file {}", outputFile.toAbsolutePath().normalize())
                Files.writeString(outputFile, this)
            }
        }
    }
}
