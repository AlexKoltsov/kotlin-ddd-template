plugins {
    id("version-catalog")
    kotlin("jvm") version "1.9.0"
}

subprojects {
    group = "com.akolts"
    version = "0.0.1"

    apply {
        plugin("version-catalog")
        plugin("org.jetbrains.kotlin.jvm")
    }

    buildscript {
        repositories {
            mavenCentral()
            mavenLocal()
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
    }

    kotlin {
        jvmToolchain(17)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
