package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("hello") {
            group = "playground"
            description = "Prints 'Hello, world!'"
            doLast {
                println("Hello, world!")
            }
        }
    }
}
