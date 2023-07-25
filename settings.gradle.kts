rootProject.name = "kotlin-ddd-template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":domain")
include(":adapter:repository:in-memory")
include(":adapter:repository:exposed")
include(":adapter:notification")
include(":adapter:verification-code-provider")
include(":app:console")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.0")
            version("kotlinx-datetime", "0.4.0")
            version("exposed", "0.41.1")
            version("postgres", "42.6.0")
            version("logback", "1.4.8")

            library("kotlinx-datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").versionRef("kotlinx-datetime")

            library("exposed-core", "org.jetbrains.exposed", "exposed-core").versionRef("exposed")
            library("exposed-jdbc", "org.jetbrains.exposed", "exposed-jdbc").versionRef("exposed")
            library("exposed-money", "org.jetbrains.exposed", "exposed-money").versionRef("exposed")
            library("exposed-kotlin-datetime", "org.jetbrains.exposed", "exposed-kotlin-datetime").versionRef("exposed")
            // wait for "exposed-json"

            library("postgres", "org.postgresql", "postgresql").versionRef("postgres")

            library("logback", "ch.qos.logback", "logback-classic").versionRef("logback")

            bundle("exposed", listOf("exposed-core", "exposed-jdbc", "exposed-money", "exposed-kotlin-datetime"))
        }
    }
}
