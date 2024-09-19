package plugins

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.gradle.api.Plugin
import org.gradle.api.Project

// ...
class ProjectManagementPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("generateReadme") {
            group = "Project Management"
            description = "Generates a README.md file with project information"

            doLast {
                val readmeContent = """
                # ${project.name}

                Versión: ${project.version}

                Fecha de creación: ${
                    Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                        .toString().split(".")[0].replace('T', ' ')
                }
                """.trimIndent()

                val readmeFile = project.layout.projectDirectory.file("README.md").asFile
                readmeFile.writeText(readmeContent)
                println("README.md generado en: ${readmeFile.absolutePath}")
            }
        }

        project.tasks.register("cleanLogs") {
            group = "Project Management"
            description = "Cleans all .log files in the logs directory"

            doLast {
                val logsDir = project.layout.projectDirectory.dir("logs").asFile
                if (logsDir.exists() && logsDir.isDirectory) {
                    val deletedCount = logsDir.listFiles { _, name -> name.endsWith(".log") }
                        ?.onEach { it.delete() }?.size ?: 0
                    println(
                        if (deletedCount > 0) "$deletedCount archivos de registro eliminados."
                        else "No se encontraron archivos de registro."
                    )
                } else {
                    println("La carpeta 'logs' no existe.")
                }
            }
        }
    }
}