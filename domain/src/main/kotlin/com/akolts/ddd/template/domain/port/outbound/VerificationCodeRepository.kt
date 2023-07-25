package com.akolts.ddd.template.domain.port.outbound

import com.akolts.ddd.template.domain.model.VerificationCode
import java.util.*

interface VerificationCodeRepository : Repository<VerificationCode, UUID> {
    fun findByUserIdAndCode(userId: UUID, code: String): VerificationCode?
}
