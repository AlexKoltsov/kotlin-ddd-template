package com.akolts.ddd.template.app.console

import com.akolts.ddd.template.domain.port.inboud.UserLoginUseCase
import com.akolts.ddd.template.domain.port.inboud.UserLogoutUseCase
import com.akolts.ddd.template.domain.port.inboud.UserRegistrationUseCase
import com.akolts.ddd.template.domain.port.inboud.UserRegistrationUseCaseCommand
import com.akolts.ddd.template.domain.port.outbound.PasswordEncoder
import com.akolts.ddd.template.domain.usecase.UserUseCases
import com.akolts.ddd.template.repository.exposed.ExposedUserRepository
import com.akolts.ddd.template.repository.exposed.initializeExposed

fun main() {
    val (
        userRegistrationUseCase,
        userLoginUseCase,
        userLogoutUseCase,
    ) = dependencies()

    userRegistrationUseCase.register(
        UserRegistrationUseCaseCommand(
            email = readln("Enter email: ").trim(),
            phone = null,
            password = readln("Enter password: ").trim()
        )
    )
}

fun readln(msg: String): String {
    println(msg)
    return readln()
}

fun dependencies(): AppDependencies {
    initializeExposed()

    val userRepository = ExposedUserRepository()
    val passwordEncoder = object : PasswordEncoder {
        override fun encode(password: String) = "encoded:$password"
    }
    val userUseCases = UserUseCases(userRepository = userRepository, passwordEncoder = passwordEncoder)
    return AppDependencies(
        userRegistrationUseCase = userUseCases,
        userLoginUseCase = userUseCases,
        userLogoutUseCase = userUseCases,
    )
}

data class AppDependencies(
    val userRegistrationUseCase: UserRegistrationUseCase,
    val userLoginUseCase: UserLoginUseCase,
    val userLogoutUseCase: UserLogoutUseCase,
)
