package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction

/**
 * A Gradle task to calculate and print the Fibonacci sequence up to the n-th number.
 *
 * ## Usage:
 * This task calculates the Fibonacci sequence for the specified input `number`, printing each value in the sequence
 * followed by the final n-th Fibonacci number. Ensure that the input number is a non-negative integer, as negative
 * values will cause the task to fail.
 *
 * ### Example 1: Calculate Fibonacci for 5
 * ```kotlin
 * tasks.register<FibonacciTask>("fibonacciTask") {
 *     number = 5
 * }
 * ```
 * The output will be:
 * ```
 * 0 1 1 2 3
 * The 5-th Fibonacci number is: 5
 * ```
 *
 * ### Example 2: Calculate Fibonacci for 10
 * ```kotlin
 * tasks.register<FibonacciTask>("fibonacciTask") {
 *     number = 10
 * }
 * ```
 * The output will be:
 * ```
 * 0 1 1 2 3 5 8 13 21 34
 * The 10-th Fibonacci number is: 55
 * ```
 *
 * @property number The number for which to calculate the Fibonacci sequence. Must be greater than or equal to 0.
 */
abstract class FibonacciTask : DefaultTask() {
    @get:Input
    abstract val number: Property<Int>

    /**
     * Calculates and prints the Fibonacci sequence up to the n-th Fibonacci number.
     *
     * ## Usage:
     * This function retrieves the value of `number`, calculates the Fibonacci sequence, prints each value in the
     * sequence, and finally prints the n-th Fibonacci number.
     *
     * @throws StopExecutionException if the provided `number` is less than 0.
     */
    @TaskAction
    fun calculateFibonacci() {
        val n = number.get().takeIf { it >= 0 }
            ?: throw StopExecutionException("The number must be greater than or equal to 0")
        var (first, second) = 0 to 1
        repeat(n) {
            print("$first ")
            second += first.also { first = second }
        }
        println("\nThe $n-th Fibonacci number is: $first")
    }
}
