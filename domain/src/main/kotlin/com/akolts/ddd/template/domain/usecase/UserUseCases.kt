package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.User
import com.akolts.ddd.template.domain.port.inboud.*
import com.akolts.ddd.template.domain.port.outbound.PasswordEncoder
import com.akolts.ddd.template.domain.port.outbound.UserRepository
import java.util.*

class UserUseCases(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserLoginUseCase, UserLogoutUseCase, UserRegistrationUseCase {

    override fun login(command: UserLoginUseCaseCommand) {
        TODO("Not yet implemented")
    }

    override fun logout(id: UUID) {
        withUser(id) {
            it.logout()
        }
    }

    override fun register(command: UserRegistrationUseCaseCommand) {
        val user = with(command) {
            User(
                email = email,
                phone = phone,
                password = passwordEncoder.encode(password),
                firstName = firstName,
                lastName = lastName,
            )
        }
        userRepository.save(user)
    }

    private fun withUser(id: UUID, block: (User) -> Unit) {
        userRepository.findById(id)
            ?.let {
                block(it)
                userRepository.save(it)
            }
    }
}