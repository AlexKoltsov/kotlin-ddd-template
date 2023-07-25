package com.akolts.ddd.template.repository.exposed

import com.akolts.ddd.template.domain.model.VerificationCode
import com.akolts.ddd.template.domain.port.outbound.VerificationCodeRepository
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ExposedVerificationCodeRepository : VerificationCodeRepository {

    override fun save(obj: VerificationCode) =
        transaction {
            VerificationCodes.insert { it.verificationCodes(obj) }
            return@transaction
        }

    override fun findById(id: UUID): VerificationCode? =
        transaction {
            VerificationCodes
                .select {
                    VerificationCodes.id eq id
                }
                .singleOrNull()
                ?.toVerificationCode()
        }

    override fun findByUserIdAndCode(userId: UUID, code: String): VerificationCode? =
        transaction {
            VerificationCodes
                .select {
                    (VerificationCodes.userId eq userId) and (VerificationCodes.code eq code)
                }
                .singleOrNull()
                ?.toVerificationCode()
        }

    override fun findAll(): List<VerificationCode> =
        transaction {
            VerificationCodes
                .selectAll()
                .map { it.toVerificationCode() }
        }

}