package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.WrongCredentials
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
        userRepository.findByEmail(command.email)
            ?.let {
                if (it.password != passwordEncoder.encode(command.password)) throw WrongCredentials("Wrong password")
                it.login()
                userRepository.save(it)
            }
    }

    override fun loginByPhone(command: PhoneUserLoginCommand) {
        userRepository.findByPhone(command.phone)
            ?.let {
                if (it.password != passwordEncoder.encode(command.password)) throw WrongCredentials("Wrong password")
                it.login()
                userRepository.save(it)
            }
    }
}