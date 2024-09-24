plugins {
    id("publish.conventions")
    id("dokka.conventions")
    alias(libs.plugins.dokka)
}

dependencies {
    implementation(libs.kotlinx.datetime)
}

artifact {
    artifactName = "${rootProject.name}-${project.name}"
    artifactVersion = project.version.toString()
}
