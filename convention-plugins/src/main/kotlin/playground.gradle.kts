import extensions.GreetExtension
import tasks.FibonacciTask
import tasks.UppercaseTask
import plugins.HelloPlugin
import plugins.VersioningPlugin
import tasks.ParityTask
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

tasks.register("printFibonacciSequence") {
    group = "Playground"
    description = "Prints the first 10 Fibonacci numbers and highlights the last one"

    val sequence = mutableListOf<Int>()

    doFirst {
        var first = 0
        var second = 1
        repeat(10) {
            sequence.add(first)
            val temp = first + second
            first = second
            second = temp
        }

        sequence.forEach(::println)
    }

    doLast {
        println("\nThe 10th Fibonacci number is: ${sequence.last()}")
    }
}

tasks.register("announceFibonacci") {
    group = "Playground"
    description = "Prints a message before calculating the Fibonacci sequence"
    doFirst {
        println("Calculating the Fibonacci sequence...")
    }
}

tasks.named("printFibonacciSequence") {
    dependsOn("announceFibonacci")
}

tasks.register("countCompiledSize") {
    group = "build"
    description = "Counts the size of the compiled classes"
    dependsOn("compileKotlin")

    doLast {
        val files = fileTree("app/build/classes/kotlin/main").files +
                fileTree("lib/build/classes/kotlin/main").files
        val totalSize = files.sumOf { it.length() }
        println("The total size of the compiled classes is $totalSize bytes")
    }
}

tasks.register<Copy>("copyCompiledClasses") {
    group = "build"
    description = "Copies compiled classes from all modules into a timestamped output directory"
    dependsOn("compileKotlin")

    val modules = listOf("app", "lib")
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"))

    from(modules.map { "$it/build/classes/kotlin/main" })
    into("compiled-classes-$timestamp")
}

tasks.register<FibonacciTask>("fib_10") {
    group = "playground"
    description = "Calculates the 10th Fibonacci number"
    number = 10
    doFirst { println("Calculating the 10th Fibonacci number...") }
    doLast { println("Calculation complete.") }
}

tasks.register<FibonacciTask>("fib_20") {
    group = "playground"
    description = "Calculates the 20th Fibonacci number"
    number = 20
    doFirst { println("Calculating the 20th Fibonacci number...") }
    doLast { println("Calculation complete.") }
}

tasks.register<UppercaseTask>("processText") {
    group = "playground"
    description = "Processes text from an input file"
    inputFile = file("input.txt")
    outputFile = file("output.txt")
    doFirst { println("Processing text...") }
    doLast { println("Processing complete.") }
}

apply<HelloPlugin>()
apply<VersioningPlugin>()

project.extensions.create<GreetExtension>("greeting")

tasks.register("greet") {
    group = "playground"
    description = "Prints a greeting message"
    doLast {
        val module = project.extensions.getByType<GreetExtension>().module
        println("Hello, from $module!")
    }
}

tasks.register("greetPrince") {
    group = "Playground"
    description = "Greets the Prince of Persia before his next mission"
    doLast {
        println("The sands of time are calling, Prince...")
    }
}

tasks.register<ParityTask>("parityTask") {
    inputFile = file("numbers.txt")
    outputFile = file("results.txt")
    doFirst {
        println("Checking parity for numbers in a file...")
    }
    doLast {
        println("Parity check complete.")
    }
}
