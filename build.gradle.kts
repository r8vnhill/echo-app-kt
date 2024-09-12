plugins {
    id("jvm.conventions")
}

val projectGroup = extra["echo.group"] ?: error("La propiedad 'echo.group' no está definida.")
val projectVersion: String = libs.versions.echo.get()

allprojects {
    group = projectGroup
    version = projectVersion
}
