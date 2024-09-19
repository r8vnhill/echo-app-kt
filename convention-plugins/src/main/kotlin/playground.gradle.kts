import extensions.GreetExtension
import tasks.FibonacciTask
import tasks.UppercaseTask
import plugins.HelloPlugin
import plugins.VersioningPlugin
import tasks.ParityTask

tasks.register("fib") {
    group = "Playground"
    description = "Calculates the Fibonacci sequence up to the 10th number"
    var first = 0
    var second = 1
    doFirst {
        repeat(10) {
            print("$first ")
            second += first
            first = second - first
        }
    }
    doLast {
        println("\nThe 10th Fibonacci number is: $first")
    }
}

tasks.register("message") {
    group = "Playground"
    description = "Prints a message before calculating the Fibonacci sequence"
    doFirst {
        println("Calculating the Fibonacci sequence...")
    }
}

tasks.named("fib") {
    dependsOn("message")
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
    description = "Copy the compiled classes to a specific directory"
    dependsOn("compileKotlin")
    from("app/build/classes/kotlin/main")
    into("compiled-classes")
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
