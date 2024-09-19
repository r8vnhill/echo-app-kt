plugins {
    id("jvm.conventions")
    id("playground")
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
}

val projectGroup = extra["echo.group"] ?: error("La propiedad 'echo.group' no está definida.")
val projectVersion: String = libs.versions.echo.get()

allprojects {
    group = projectGroup
    version = projectVersion
}

val detektId = libs.plugins.detekt.get().pluginId
val detektFormattingModule = libs.detekt.formatting.get().module.toString()
val detektFormattingVersion = libs.detekt.formatting.get().version

subprojects {
    apply(plugin = "jvm.conventions")
    apply(plugin = detektId)

    dependencies {
        detektPlugins("$detektFormattingModule:$detektFormattingVersion")
    }
}

greeting {
    module = name
}
