package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.UserNotFoundByEmailException
import com.akolts.ddd.template.domain.UserNotFoundByPhoneException
import com.akolts.ddd.template.domain.WrongCredentials
import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.port.inboud.EmailUserLoginCommand
import com.akolts.ddd.template.domain.port.inboud.PhoneUserLoginCommand
import com.akolts.ddd.template.domain.port.inboud.UserLoginUseCase
import com.akolts.ddd.template.domain.port.outbound.PasswordEncoder
import com.akolts.ddd.template.domain.port.outbound.UserRepository

class UserLoginUseCaseImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserLoginUseCase {

    override fun loginByEmail(command: EmailUserLoginCommand) {
        val user = userRepository.findByEmail(command.email) ?: throw UserNotFoundByEmailException(command.email)
        login(user, command.password)
    }

    override fun loginByPhone(command: PhoneUserLoginCommand) {
        val user = userRepository.findByPhone(command.phone) ?: throw UserNotFoundByPhoneException(command.phone)
        login(user, command.password)
    }

    private fun login(user: User, password: String) {
        if (user.password != passwordEncoder.encode(password)) throw WrongCredentials("Wrong password")
        user.login()
        userRepository.save(user)
    }
}