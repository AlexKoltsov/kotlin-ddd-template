package com.akolts.ddd.template.repository.exposed

import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.port.outbound.UserRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ExposedUserRepository : UserRepository {

    override fun save(obj: User) =
        transaction {
            Users.insert { it.users(obj) }
            UserProfiles.insert { it.userProfiles(obj) }
            SessionInfos.insert { it.sessionInfos(obj) }
            return@transaction
        }

    override fun findById(id: UUID): User? =
        transaction {
            Users
                .innerJoin(UserProfiles)
                .innerJoin(SessionInfos)
                .select {
                    Users.id eq id
                }
                .singleOrNull()
                ?.toDomain()
        }

    override fun findByEmail(email: String): User? =
        transaction {
            Users
                .innerJoin(UserProfiles)
                .innerJoin(SessionInfos)
                .select {
                    Users.email eq email
                }
                .singleOrNull()
                ?.toDomain()
        }

    override fun findByPhone(phone: String): User? =
        transaction {
            Users
                .innerJoin(UserProfiles)
                .innerJoin(SessionInfos)
                .select {
                    Users.phone eq phone
                }
                .singleOrNull()
                ?.toDomain()
        }

    override fun findAll(): List<User> =
        transaction {
            Users
                .innerJoin(UserProfiles)
                .innerJoin(SessionInfos)
                .selectAll()
                .map { it.toDomain() }
        }
}