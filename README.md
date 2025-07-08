# echo-app-kt: A simple Kotlin CLI to understand Gradle and the Kotlin ecosystem

[![License: BSD-2-Clause](https://img.shields.io/badge/License-BSD%202--Clause-blue.svg)](./LICENSE)
[![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue?logo=kotlin)](https://kotlinlang.org/docs/getting-started.html)
[![Educational](https://img.shields.io/badge/purpose-educational-yellow)](https://dibs.ravenhill.cl)
[![Status](https://img.shields.io/badge/status-stable-brightgreen)]()
[![DIBS Site](https://img.shields.io/badge/website-dibs.ravenhill.cl-purple)](https://dibs.ravenhill.cl)

A command-line application written in **Kotlin**, designed to introduce **Gradle** and the **Kotlin ecosystem** through a clean, idiomatic, and reproducible example. This project accompanies lessons from
the [DIBS course](https://dibs.ravenhill.cl), focused on building well-structured and maintainable software libraries.

> [!note]
> The course is taught in Spanish, but **all repository content is in English** to ensure accessibility and broader
> adoption.

## 📖 Table of Contents

- [echo-app-kt: A simple Kotlin CLI to understand Gradle and the Kotlin ecosystem](#echo-app-kt-a-simple-kotlin-cli-to-understand-gradle-and-the-kotlin-ecosystem)
  - [📖 Table of Contents](#-table-of-contents)
  - [🎓 Lessons](#-lessons)
  - [🔍 Overview](#-overview)
  - [📝 Getting Started](#-getting-started)
    - [Prerequisites](#prerequisites)
    - [Clone \& Compile](#clone--compile)
  - [🛡️ License](#️-license)
  - [🌐 DIBS Website](#-dibs-website)

## 🎓 Lessons

This repository is part of the following DIBS course lesson:

- 📘 **[Creating a Basic Project with Gradle](https://dibs.ravenhill.cl/docs/build-systems/init/gradle)**  
    Learn how to set up a basic (empty) Gradle project with Kotlin, including the necessary files and structure to get started.

## 🔍 Overview

This application is intentionally simple — its purpose is not to showcase complex logic, but to serve as an **educational scaffold** to explore:

- The role of Gradle and the `build.gradle.kts` file
- The structure of a modern Kotlin project
- How the `gradle` CLI facilitates creation, compilation, and execution
- Best practices for reproducible builds and configuration

The program simply echoes messages passed as arguments — but the lesson is in the setup, not the output.

## 📝 Getting Started

### Prerequisites

- [JDK 11 or later](https://openjdk.java.net/install/)
- PowerShell (Windows) or a terminal with `bash`
- Git

> See the [lesson documentation](https://dibs.ravenhill.cl/docs/installation) for OS-specific install
> scripts.

### Clone & Compile

```bash
git clone https://github.com/r8vnhill/echo-app-kt.git
cd echo-app-kt
./gradlew build
```

## 🛡️ License

Released under the **[BSD 2-Clause License](./LICENSE)**.

You are free to use, adapt, and share this code in personal or educational contexts, as long as attribution is provided.

## 🌐 DIBS Website

The full course — *Diseño e Implementación de Bibliotecas de Software* — is available at:

👉 [https://dibs.ravenhill.cl](https://dibs.ravenhill.cl)

There you'll find complete lessons, slides, exercises, and complementary resources (in Spanish).