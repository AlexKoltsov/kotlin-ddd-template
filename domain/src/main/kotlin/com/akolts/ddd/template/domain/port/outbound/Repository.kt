package com.akolts.ddd.template.domain.port.outbound

interface Repository<T, ID> {
    fun save(obj: T)
    fun findById(id: ID): T?
    fun findAll(): List<T>
}