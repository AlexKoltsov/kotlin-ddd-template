package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.UserNotFoundException
import com.akolts.ddd.template.domain.port.inboud.UserLogoutUseCase
import com.akolts.ddd.template.domain.port.outbound.UserRepository
import java.util.*

class UserLogoutUseCaseImpl(
    private val userRepository: UserRepository,
) : UserLogoutUseCase {

    override fun logout(id: UUID) {
        val user = userRepository.findById(id) ?: throw UserNotFoundException(id)
        user.logout()
        userRepository.save(user)
    }
}