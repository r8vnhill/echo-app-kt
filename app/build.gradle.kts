plugins {
    application
}

dependencies {
    implementation(libs.echo)
}

application {
    mainClass.set("cl.ravenhill.echo.EchoAppKt")
}

tasks.named("compileKotlin") {
    dependsOn(":lib:copyLib")
}
