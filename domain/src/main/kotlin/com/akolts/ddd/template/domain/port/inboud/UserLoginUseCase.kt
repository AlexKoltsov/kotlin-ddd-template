package com.akolts.ddd.template.domain.port.inboud

interface UserLoginUseCase {
    fun loginByEmail(command: EmailUserLoginCommand)
    fun loginByPhone(command: PhoneUserLoginCommand)
}

data class EmailUserLoginCommand(
    val email: String,
    val password: String,
)

data class PhoneUserLoginCommand(
    val phone: String,
    val password: String,
)