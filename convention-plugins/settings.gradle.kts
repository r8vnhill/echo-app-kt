rootProject.name = "convention-plugins"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

// ...
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    // Definición de un catálogo centralizado de versiones de dependencias
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}