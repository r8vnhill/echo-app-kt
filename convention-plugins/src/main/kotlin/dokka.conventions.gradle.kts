import org.jetbrains.dokka.gradle.DokkaTask

tasks.withType<DokkaTask>().configureEach {   // (1)
    dokkaSourceSets {
        configureEach {   // (2)
            reportUndocumented = true  // (3)
            sourceRoots.from(file("src/main/kotlin"))   // (4)
            platform = org.jetbrains.dokka.Platform.jvm  // (5)
        }
    }
    outputDirectory = layout.buildDirectory.dir("dokka/html").get().asFile  // (6)
}

tasks.register<Jar>("dokkaJar") {
    group = "documentation"
    description = "Creates a JAR with the Dokka documentation"
    archiveClassifier = "docs"
    from(tasks.named<DokkaTask>("dokkaHtml").get().outputDirectory)
}
