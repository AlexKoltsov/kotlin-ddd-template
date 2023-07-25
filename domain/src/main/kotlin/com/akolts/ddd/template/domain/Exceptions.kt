package com.akolts.ddd.template.domain

import java.util.*

class WrongCredentials(message: String?) : Throwable(message = message)

class UserNotFoundException(userId: UUID) : Throwable(message = "User with ID '$userId' not found")

class VerificationCodeNotFoundException(userId: UUID, code: String) :
    Throwable(message = "Verification code '$code' for user ID '$userId' not found")