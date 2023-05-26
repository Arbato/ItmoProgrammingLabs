plugins {
    application
    kotlin("jvm") version "1.7.21"
}

group = "org.example.server"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    "implementation"("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.85.0")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("org.codehaus.jackson:jackson-mapper-asl:1.9.13")

}

application {
    mainClass.set("MainKt")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "MainKt"
    from(configurations.runtimeClasspath.get().map { zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("dependencies", "compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}

