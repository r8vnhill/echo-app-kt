package plugins

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A Gradle plugin that generates a version file containing the project version and the build date.
 *
 * This plugin adds a task named `writeVersionInfo` to the project, which generates a `version.txt` file in the build
 * directory. The file contains the project's version and the current build date. If the version file already exists, it
 * appends the new version and build date to the file.
 *
 * ## Usage:
 * Apply this plugin to automatically generate a `version.txt` file with the project's version and build date.
 *
 * ### Example 1: Applying the plugin in a build script
 * ```kotlin
 * apply<VersioningPlugin>()
 * ```
 * After applying the plugin, the `writeVersionInfo` task can be run to generate or update the version file.
 *
 * Run the task using:
 * ```bash
 * ./gradlew writeVersionInfo
 * ```
 * This will create or update the `version.txt` file in the build directory with the current project version and build
 * date.
 */
class VersioningPlugin : Plugin<Project> {

    /**
     * Applies the plugin to the given project by registering the `writeVersionInfo` task.
     *
     * This method registers a task named `writeVersionInfo` in the project. The task generates a file
     * named `version.txt` in the project's build directory, containing the project's version and the current build
     * date.
     *
     * - The project's version is obtained using `project.version`.
     * - The current date and time are fetched and formatted without milliseconds.
     * - If the version file already exists, new data is appended; otherwise, a new file is created.
     *
     * The task prints the path to the version file after it is updated.
     *
     * @param project The Gradle project to which this plugin is applied.
     */
    override fun apply(project: Project) {
        project.tasks.register("writeVersionInfo") {
            group = "Versioning"
            description = "Generates a version file with the project version and build date"
            doLast {
                val version = project.version.toString()
                val currentMoment = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val dateTime = currentMoment.toString().split('.')[0].replace('T', ' ')
                val versionFile = project.layout.buildDirectory.file("version.txt").get().asFile
                if (!versionFile.exists()) {
                    versionFile.createNewFile()
                }
                versionFile.appendText("Version: $version\nBuild Date: $dateTime\n\n")
                println("Version file updated at: ${versionFile.absolutePath}")
            }
        }
    }
}
