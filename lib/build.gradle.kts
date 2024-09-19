plugins {
    id("compile.conventions")
    id("dokka.conventions")
    alias(libs.plugins.dokka)
}

dependencies {
    implementation(libs.kotlinx.datetime)
}

fatJar {
    implementationTitle = project.name
    implementationVersion = project.version.toString()
}

