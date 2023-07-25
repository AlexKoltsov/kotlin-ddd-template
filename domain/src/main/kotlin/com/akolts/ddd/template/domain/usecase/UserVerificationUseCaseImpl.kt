package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.UserNotFoundException
import com.akolts.ddd.template.domain.VerificationCodeNotFoundException
import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.port.inboud.UserVerificationUseCase
import com.akolts.ddd.template.domain.port.outbound.UserRepository
import com.akolts.ddd.template.domain.port.outbound.VerificationCodeRepository
import java.util.*

class UserVerificationUseCaseImpl(
    private val userRepository: UserRepository,
    private val verificationCodeRepository: VerificationCodeRepository,
) : UserVerificationUseCase {

    override fun verifyEmail(userId: UUID, verificationCode: String) {
        verifyRoutine(userId, verificationCode) { it.phoneConfirmed = true }
    }

    override fun verifyPhone(userId: UUID, verificationCode: String) {
        verifyRoutine(userId, verificationCode) { it.phoneConfirmed = true }
    }

    private fun verifyRoutine(userId: UUID, verificationCode: String, ifSuccess: (User) -> Unit) {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException(userId)
        verificationCodeRepository.findByUserIdAndCode(userId = userId, code = verificationCode)
            ?.also {
                ifSuccess(user)
                userRepository.save(user)
            }
            ?: throw VerificationCodeNotFoundException(userId = userId, code = verificationCode)
    }
}