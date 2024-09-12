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
    }
}