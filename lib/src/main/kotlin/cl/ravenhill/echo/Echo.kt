package cl.ravenhill.echo

import kotlinx.datetime.Clock

/**
 * Returns a formatted message with the current timestamp and the provided message.
 *
 * This function retrieves the current system time and appends it to the given message, returning the result as a
 * formatted string. The timestamp includes the date and time at the moment the function is called.
 *
 * ## Usage:
 * This function can be used to log messages with a timestamp for debugging or tracking purposes.
 *
 * ### Example:
 * ```kotlin
 * val logMessage = echo("Task started")
 * println(logMessage)
 * ```
 * Output:
 * ```
 * 2024-09-15T10:45:30.123456Z - Task started
 * ```
 *
 * @param message The message to be echoed with the current timestamp.
 * @return A string that combines the current system time with the provided message.
 */
fun echo(message: String): String = "${Clock.System.now()} - $message"
