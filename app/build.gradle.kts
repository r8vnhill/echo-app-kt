plugins {
    id("jvm.conventions")
    application
}

dependencies {
    implementation(libs.kotlinx.datetime)
}

application {
    mainClass.set("cl.ravenhill.echo.EchoAppKt")
}
