package cl.ravenhill.echo

import kotlinx.datetime.Clock

fun echo(message: String) = "${Clock.System.now()} - $message"

fun main(args: Array<String>) {
    for (arg in args) {
        println(echo(arg))
    }
}