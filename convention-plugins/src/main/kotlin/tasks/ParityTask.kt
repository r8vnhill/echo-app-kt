package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.IOException

/**
 * A Gradle task that processes numbers from an input file and identifies whether each number is even or odd.
 *
 * This task reads lines from the `inputFile`, checks if each line can be parsed as an integer, and determines whether
 * the number is even or odd. If a line does not represent a valid integer, it will output an error message stating that
 * the line is not a valid number. The results are written to the `outputFile`.
 *
 * ## Usage:
 * This task is useful for reading a file of numbers, checking the parity of each, and saving the result to an output
 * file.
 *
 * ### Example 1: Checking parity for numbers in a file
 * ```kotlin
 * tasks.register("parityTask", ParityTask::class) {
 *     inputFile = file("path/to/numbers.txt")
 *     outputFile = file("path/to/results.txt")
 * }
 * ```
 * After the task runs, each number from `numbers.txt` will be processed and its parity will be written to
 * `results.txt`.
 * Non-number lines will be identified as invalid.
 *
 * @property inputFile The input file containing numbers, one per line. Non-number lines will be handled with an error
 *   message.
 * @property outputFile The file where the results, including the parity of each number, will be written.
 */
abstract class ParityTask : DefaultTask() {
    @get:InputFile
    abstract var inputFile: File

    @get:OutputFile
    abstract var outputFile: File

    /**
     * Processes each line in the input file, determines whether the number is even or odd, and writes the results to
     * the output file.
     *
     * For each line in the `inputFile`, this function checks if the line can be parsed as an integer. If valid, it
     * determines if the number is even ("par") or odd ("impar") and writes the result to the `outputFile`. If a line
     * cannot be parsed as a valid number, it outputs an error message stating that the line is not a valid number.
     *
     * @throws NumberFormatException if a line cannot be parsed as an integer.
     * @throws IOException if the input file cannot be read or the output file cannot be written.
     */
    @TaskAction
    fun processNumbers() = inputFile.readLines().map { line ->
        try {
            val num = line.toInt()
            "$num: ${if (num % 2 == 0) "par" else "impar"}"
        } catch (e: NumberFormatException) {
            "$line: no es un número válido"
        }
    }.let { results -> outputFile.writeText(results.joinToString("\n")) }
}
