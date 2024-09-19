plugins {
    application
}

dependencies {
    implementation(
        fileTree("libs") {
            include("lib-1.0.0-all.jar")
        }
    )
}

application {
    mainClass.set("cl.ravenhill.echo.EchoAppKt")
}

tasks.named("compileKotlin") {
    dependsOn(":lib:copyLib")
}
