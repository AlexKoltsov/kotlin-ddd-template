package com.akolts.ddd.template.domain

import java.util.*

class WrongCredentials(message: String?) : Throwable(message = message)

class UserNotFoundException(userId: UUID) : Throwable(message = "User with ID '$userId' not found")

class UserNotFoundByEmailException(email: String) : Throwable(message = "User with email '$email' not found")

class UserNotFoundByPhoneException(phone: String) : Throwable(message = "User with phone '$phone' not found")

class UserByEmailAlreadyExistsException(email: String) : Throwable(message = "User with email '$email' already exists")

class UserByPhoneAlreadyExistsException(phone: String) : Throwable(message = "User with phone '$phone' already exists")

class VerificationCodeNotFoundException(userId: UUID, code: String) :
    Throwable(message = "Verification code '$code' for user ID '$userId' not found")