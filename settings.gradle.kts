rootProject.name = "echo-app"

include(
    ":app",
    ":lib"
)

pluginManagement {
    includeBuild("convention-plugins") // Incluye el módulo de configuración de plugins
    repositories {
        mavenCentral()        // Repositorio Maven Central
        gradlePluginPortal()  // Portal de plugins de Gradle
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()

        maven {
            url = uri("https://maven.pkg.github.com/r8vnhill/echo-app-kt")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
