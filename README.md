# echo-app-kt 🗣️

![Kotlin](https://img.shields.io/badge/kotlin-2.1.20-blue?logo=kotlin)
![Gradle](https://img.shields.io/badge/Gradle-8.x-blue?logo=gradle)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Platform](https://img.shields.io/badge/JVM-Java_21-blueviolet)

A Kotlin multi-module project demonstrating the use of convention plugins to centralize build configuration and promote consistency across subprojects.

## 📦 Overview

This project showcases how to set up and apply a custom convention plugin using Kotlin DSL in Gradle. Convention plugins are ideal for sharing common configuration logic such as:

- Applying shared plugins like `kotlin("jvm")`
- Setting a unified Java toolchain version
- Managing dependencies using a central version catalog (`libs.versions.toml`)

By applying the convention plugin to each subproject, we ensure consistent build behavior with minimal duplication.

## 📁 Project Structure

```plaintext
.
├── app/                 # Application module
│   └── src/main/kotlin/
├── lib/                 # Library module
│   └── src/main/kotlin/
├── convention-plugins/  # Custom convention plugin module
│   └── src/main/kotlin/
├── gradle/
│   └── wrapper/
└── settings.gradle.kts  # Includes the convention plugin module
```

## 🔧 How It Works

The `convention-plugins` module contains reusable Gradle scripts, including:

```kotlin
// convention-plugins/src/main/kotlin/jvm.conventions.gradle.kts

plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

This script applies the Kotlin JVM plugin and configures Java 21 as the toolchain.

### 🔗 Centralized Dependency Management

The project uses a shared `libs.versions.toml` file in `gradle/`:

```toml
[versions]
kotlin = "2.1.20"
```

This version is used consistently across all modules by referencing `libs.kotlin.gradle.plugin` inside the convention plugin's `build.gradle.kts`.

### ⚠️ Note on `@Suppress("UnstableApiUsage")`

Gradle currently treats the `repositories` block inside `dependencyResolutionManagement` as unstable, emitting a warning. We suppress this warning explicitly to keep builds clean:

```kotlin
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    // ...
}
```

## 🚀 How to Build

To compile the entire project:

```bash
./gradlew build
```

To verify that the Kotlin plugin was correctly applied to `app`, run:

```bash
./gradlew --quiet app:dependencies | grep kotlin
```

If you see Kotlin-related dependencies, your convention plugin is working 🎉

## 💡 Why This Matters

Using convention plugins early in your project helps:

- Reduce boilerplate across modules
- Improve maintainability and scalability
- Ensure consistent configuration across teams

## 🛠 Kotlin Version

This project uses **Kotlin 2.1.20**.
