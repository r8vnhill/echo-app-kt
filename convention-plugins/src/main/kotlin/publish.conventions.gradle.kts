import extensions.ArtifactExtension
import extensions.FatJarExtension

plugins {
    id("compile.conventions")
    id("dokka.conventions")
    `maven-publish`
}

project.extensions.create<ArtifactExtension>("artifact")

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
                    username = System.getenv("GITHUB_USER") ?: error("GITHUB_USER is not set")
                    password = System.getenv("GITHUB_TOKEN") ?: error("GITHUB_TOKEN is not set")
                }
            }
        }
    }
}
