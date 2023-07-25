dependencies {
    implementation(projects.domain)
    implementation(libs.bundles.exposed)

    runtimeOnly(libs.postgres)
}
