package com.akolts.ddd.template.domain.port.inboud

import java.util.*

interface UserVerificationUseCase {
    fun verifyEmail(userId: UUID, verificationCode: String)
    fun verifyPhone(userId: UUID, verificationCode: String)
}