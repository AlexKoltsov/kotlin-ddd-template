package com.akolts.ddd.template.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.*

class User(
    private val email: String?,
    private val phone: String?,
    private val password: String,
    private var firstName: String?,
    private var lastName: String?,
) {

    private val id: UUID = UUID.randomUUID()
    private val registrationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    private var lastLogin: LocalDateTime? = null

    fun logout() {
        TODO("Not yet implemented")
    }
}