package com.akolts.ddd.template.domain.port.inboud

interface UserRegistrationUseCase {
    fun registerByEmail(command: EmailUserRegistrationCommand)
    fun registerByPhone(command: PhoneUserRegistrationCommand)
}

data class EmailUserRegistrationCommand(
    val email: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
)

data class PhoneUserRegistrationCommand(
    val phone: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
)