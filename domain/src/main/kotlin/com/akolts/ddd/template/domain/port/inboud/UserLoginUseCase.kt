package com.akolts.ddd.template.domain.port.inboud

interface UserLoginUseCase {
    fun login(command: UserLoginUseCaseCommand)
}

data class UserLoginUseCaseCommand(
    val email: String?,
    val phone: String?,
    val password: String,
)