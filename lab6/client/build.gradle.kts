plugins {
    application
    kotlin("jvm") version "1.7.21"
    //id("org.jetbrains.kotlin.plugin.serialization") version "1.7.21"
    //id("org.jetbrains.dokka") version "1.7.20"
}


group = "org.example.client"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    "implementation"(project(":constituents"))
        testImplementation(kotlin("test"))
        implementation("io.github.pdvrieze.xmlutil:core-jvm:0.85.0")
        implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
        implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
        implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.2")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda:2.14.2")
        implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
        implementation("org.codehaus.jackson:jackson-mapper-asl:1.9.13")

    //"implementation"("org.valiktor:valiktor-core:0.12.0")
    //"implementation"("io.insert-koin:koin-core:3.3.3")
    //"implementation"("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    //"implementation"("org.jetbrains.kotlin:kotlin-stdlib")
    //"testImplementation"("org.jetbrains.kotlin:kotlin-test")
    //"testImplementation"("io.mockk:mockk:1.13.4")
}

application {
    mainClass.set("MainKt")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}


tasks.jar {
    manifest.attributes["Main-Class"] = "MainKt"
    from(
        configurations
            .runtimeClasspath
            .get()
            .map { zipTree(it) }
    )
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
