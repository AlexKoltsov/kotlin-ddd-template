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
            from(files("libs.versions.toml"))
        }
    }
}