rootProject.name = "kotlin-ddd-template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":domain")
include(":adapter:repository:in-memory")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.0")
            version("kotlinx-datetime", "0.4.0")

            library("kotlinx-datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").versionRef("kotlinx-datetime")
        }
    }
}
