dependencies {
    implementation(projects.domain)
    implementation(projects.adapter.repository.inMemory)
    implementation(projects.adapter.repository.exposed)
    implementation(projects.adapter.notification)
    implementation(projects.adapter.verificationCodeProvider)
}
