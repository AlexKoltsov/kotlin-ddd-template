package com.akolts.ddd.template.domain.port.inboud

interface UserRegistrationUseCase {
    fun register(command: UserRegistrationUseCaseCommand)
}

data class UserRegistrationUseCaseCommand(
    val email: String?,
    val phone: String?,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
)