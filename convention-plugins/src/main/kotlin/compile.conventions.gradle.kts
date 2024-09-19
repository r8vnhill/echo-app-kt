import extensions.FatJarExtension
import java.time.LocalDateTime

plugins {
    id("jvm.conventions")
    id("dokka.conventions")
}

tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Creates a fat JAR with all dependencies"

    val fatJarConfig = project.extensions.getByType<FatJarExtension>()

    archiveClassifier = "all"   // (3)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE // (4)
    manifest {  // (5)
        attributes["Implementation-Title"] = fatJarConfig.implementationTitle
        attributes["Implementation-Version"] = fatJarConfig.implementationVersion
        attributes["Build-Date"] = LocalDateTime.now().toString()
    }
    from(sourceSets.main.get().output)  // (6)
    dependsOn(configurations.runtimeClasspath)  // (7)
    from({  // (8)
        configurations.runtimeClasspath.get()
            .filter { file -> file.name.endsWith("jar") }   // (9)
            .map { file -> zipTree(file) }  // (10)
    })
}

tasks.named("fatJar") {
    dependsOn("dokkaJar")
}
