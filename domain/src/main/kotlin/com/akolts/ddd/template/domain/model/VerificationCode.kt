package com.akolts.ddd.template.domain.model

import java.util.*

data class VerificationCode(
    val userId: UUID,
    val code: String,
    val id: UUID = UUID.randomUUID(),
)