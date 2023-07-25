package com.akolts.ddd.template.repository.`in`.memory

import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.port.outbound.UserRepository
import java.util.*

class InMemoryUseRepository : UserRepository {

    private val map: MutableMap<UUID, User> = mutableMapOf()

    override fun save(obj: User) {
        map[obj.id] = obj
    }

    override fun findById(id: UUID): User? = map[id]

    override fun findByEmail(email: String): User? = findAll().find { it.email == email }

    override fun findByPhone(phone: String): User? = findAll().find { it.phone == phone }

    override fun findAll(): List<User> = map.values.toList()
}