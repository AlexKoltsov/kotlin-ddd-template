package com.akolts.ddd.template.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.*

class User(
    val email: String?,
    val phone: String?,
    val password: String,
    var firstName: String?,
    var lastName: String?,
    val id: UUID = UUID.randomUUID(),
    val registrationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    var lastLoginDate: LocalDateTime? = null,
) {

    fun logout() {
        TODO("Not yet implemented")
    }
}