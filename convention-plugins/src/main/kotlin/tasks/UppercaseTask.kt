package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.IOException
import java.util.*

/**
 * A Gradle task for processing text from an input file, converting it to uppercase, and writing the result to an output
 * file.
 *
 * The task reads the content of an `inputFile`, transforms it to uppercase using the default locale, and writes the
 * transformed text to an `outputFile`. The task expects both the input and output files to be specified.
 *
 * ## Usage:
 * This task is used to process a text file, converting all its content to uppercase and saving it to a specified output
 * file.
 *
 * ### Example 1: Processing text from an input file
 * ```kotlin
 * tasks.register<UppercaseTask>("processText") {
 *     inputFile = file("path/to/input.txt")
 *     outputFile = file("path/to/output.txt")
 * }
 * ```
 * When this task runs, the content of `input.txt` will be read, converted to uppercase, and saved to `output.txt`.
 *
 * @property inputFile The input file to be processed. This file must exist and contain text.
 * @property outputFile The file where the processed text will be written.
 */
abstract class UppercaseTask : DefaultTask() {
    @get:InputFile
    abstract var inputFile: File

    @get:OutputFile
    abstract var outputFile: File

    /**
     * Processes the input file by converting its text content to uppercase and writing the result to the output file.
     *
     * This function reads the entire content of `inputFile`, converts it to uppercase using the default locale, and
     * writes the result to `outputFile`. Ensure both the input and output files are properly configured.
     *
     * @throws IOException if the input file cannot be read or the output file cannot be written.
     */
    @TaskAction
    fun processText() {
        val processedText = inputFile.readText()
            .uppercase(Locale.getDefault())
        outputFile.writeText(processedText)
    }
}
