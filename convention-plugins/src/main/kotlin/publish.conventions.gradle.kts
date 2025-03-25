import extensions.ArtifactExtension
import extensions.FatJarExtension
import java.util.*

plugins {
    id("compile.conventions")
    id("dokka.conventions")
    `maven-publish`
}

project.extensions.create<ArtifactExtension>("artifact")

val githubProperties = Properties().apply {
    load(rootProject.file("github.properties").reader())
}

val githubToken: String = githubProperties.getProperty("github.token")
val githubUser: String = githubProperties.getProperty("github.username")

afterEvaluate {
    val artifactExtension = project.extensions.getByType<ArtifactExtension>()

    project.extensions.getByType<FatJarExtension>().apply {
        implementationTitle = artifactExtension.artifactName
        implementationVersion = artifactExtension.artifactVersion
    }

    publishing {
        publications {
            create<MavenPublication>("mavenKotlin") {
                groupId = project.group.toString()
                artifactId = artifactExtension.artifactName
                version = artifactExtension.artifactVersion

                from(components["kotlin"])

                artifact(tasks["dokkaJar"])
                artifact(tasks["fatJar"])
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/r8vnhill/echo-app-kt")

                credentials {
                    username = githubUser
                    password = githubToken
                }
            }
        }
    }
}
