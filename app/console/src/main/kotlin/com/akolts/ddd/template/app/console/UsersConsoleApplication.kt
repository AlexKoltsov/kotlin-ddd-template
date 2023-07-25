package com.akolts.ddd.template.app.console

import com.akolts.ddd.template.adapter.notification.LoggingNotifier
import com.akolts.ddd.template.adapter.verification.code.provider.RandomVerificationCodeProvider
import com.akolts.ddd.template.app.console.ACTIONS.*
import com.akolts.ddd.template.domain.port.inboud.*
import com.akolts.ddd.template.domain.port.outbound.Notifier
import com.akolts.ddd.template.domain.port.outbound.PasswordEncoder
import com.akolts.ddd.template.domain.usecase.UserLoginUseCaseImpl
import com.akolts.ddd.template.domain.usecase.UserLogoutUseCaseImpl
import com.akolts.ddd.template.domain.usecase.UserRegistrationUseCaseImpl
import com.akolts.ddd.template.domain.usecase.UserVerificationUseCaseImpl
import com.akolts.ddd.template.repository.exposed.ExposedUserRepository
import com.akolts.ddd.template.repository.exposed.ExposedVerificationCodeRepository
import com.akolts.ddd.template.repository.exposed.initializeExposed
import java.util.*

enum class ACTIONS {
    REGISTER_BY_EMAIL,
    REGISTER_BY_PHONE,
    VERIFY_BY_EMAIL,
    VERIFY_BY_PHONE,
    LOGIN_BY_EMAIL,
    LOGIN_BY_PHONE,
    LOGOUT,
}

fun main() {
    val (
        userRegistrationUseCase,
        userLoginUseCase,
        userLogoutUseCase,
        userVerificationUseCase,
    ) = dependencies()

    while (true) {
        val action = readlnWithMsg("Choose action: ${ACTIONS.entries.joinToString()}")
        when (valueOf(action)) {
            REGISTER_BY_EMAIL -> userRegistrationUseCase.registerByEmail(
                EmailUserRegistrationCommand(
                    email = readlnWithMsg("Enter email: ").trim(),
                    password = readlnWithMsg("Enter password: ").trim()
                )
            )

            REGISTER_BY_PHONE -> userRegistrationUseCase.registerByPhone(
                PhoneUserRegistrationCommand(
                    phone = readlnWithMsg("Enter phone: ").trim(),
                    password = readlnWithMsg("Enter password: ").trim()
                )
            )

            LOGIN_BY_EMAIL -> userLoginUseCase.loginByEmail(
                EmailUserLoginCommand(
                    email = readlnWithMsg("Enter email: ").trim(),
                    password = readlnWithMsg("Enter password: ").trim()
                )
            )

            LOGIN_BY_PHONE -> userLoginUseCase.loginByPhone(
                PhoneUserLoginCommand(
                    phone = readlnWithMsg("Enter phone: ").trim(),
                    password = readlnWithMsg("Enter password: ").trim()
                )
            )

            LOGOUT -> userLogoutUseCase.logout(id = readlnWithMsg("Enter user ID: ").trim().let { UUID.fromString(it) })

            VERIFY_BY_EMAIL -> userVerificationUseCase.verifyEmail(
                userId = readlnWithMsg("Enter user ID: ").trim().let { UUID.fromString(it) },
                verificationCode = readlnWithMsg("Enter verification code: ").trim()
            )

            VERIFY_BY_PHONE -> userVerificationUseCase.verifyPhone(
                userId = readlnWithMsg("Enter user ID: ").trim().let { UUID.fromString(it) },
                verificationCode = readlnWithMsg("Enter verification code: ").trim()
            )
        }
    }
}

fun readlnWithMsg(msg: String): String {
    println(msg)
    return readln()
}

fun dependencies(): AppDependencies {
    initializeExposed()

    val userRepository = ExposedUserRepository()
    val verificationCodeRepository = ExposedVerificationCodeRepository()
    val passwordEncoder = object : PasswordEncoder {
        override fun encode(password: String) = "encoded:$password"
    }
    val notifier: Notifier = LoggingNotifier()
    return AppDependencies(
        userRegistrationUseCase = UserRegistrationUseCaseImpl(
            userRepository = userRepository,
            verificationCodeRepository = verificationCodeRepository,
            passwordEncoder = passwordEncoder,
            notifier = notifier,
            verificationCodeProvider = RandomVerificationCodeProvider(),
        ),
        userLoginUseCase = UserLoginUseCaseImpl(userRepository = userRepository, passwordEncoder = passwordEncoder),
        userLogoutUseCase = UserLogoutUseCaseImpl(userRepository = userRepository),
        userVerificationUseCase = UserVerificationUseCaseImpl(
            userRepository = userRepository,
            verificationCodeRepository = verificationCodeRepository,
        )
    )
}

data class AppDependencies(
    val userRegistrationUseCase: UserRegistrationUseCase,
    val userLoginUseCase: UserLoginUseCase,
    val userLogoutUseCase: UserLogoutUseCase,
    val userVerificationUseCase: UserVerificationUseCase,
)
