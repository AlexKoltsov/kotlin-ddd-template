rootProject.name = "kotlin-ddd-template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":domain")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
//            version("kwlp", "0.0.1")
//            version("kotlin", "1.9.0")
//            version("kotlinx-coroutines", "1.7.2")
//            version("exposed", "0.41.1")
//            version("postgres", "42.6.0")
//            version("money", "0.0.2")
//            version("spring-boot", "2.7.13")
//
//            library("money", "com.mwl", "money-strict-api").versionRef("money")
//
//            library("kotlinx-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").versionRef("kotlinx-coroutines")
//
//            library("exposed-core", "org.jetbrains.exposed", "exposed-core").versionRef("exposed")
//            library("exposed-jdbc", "org.jetbrains.exposed", "exposed-jdbc").versionRef("exposed")
//            library("exposed-money", "org.jetbrains.exposed", "exposed-money").versionRef("exposed")
//            library("exposed-java-time", "org.jetbrains.exposed", "exposed-java-time").versionRef("exposed")
//            // wait for "exposed-json"
//
//            library("postgres", "org.postgresql", "postgresql").versionRef("postgres")
//
//            library("spring-actuator", "org.springframework.boot", "spring-boot-starter-actuator").withoutVersion()
//            library("spring-validation", "org.springframework.boot", "spring-boot-starter-validation").withoutVersion()
//            library("spring-web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
//            library("spring-configuration-processor", "org.springframework.boot", "spring-boot-configuration-processor").withoutVersion()
//            library("spring-test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
//
//            bundle("exposed", listOf("exposed-core", "exposed-jdbc", "exposed-money", "exposed-java-time"))
        }
    }
}
