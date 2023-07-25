package com.akolts.ddd.template.domain.model

import kotlinx.datetime.Clock.System.now
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
    val registrationDate: LocalDateTime = now().toLocalDateTime(TimeZone.UTC),
    var lastLoginDate: LocalDateTime? = null,
    var lastLogoutDate: LocalDateTime? = null,
    var emailConfirmed: Boolean = false,
    var phoneConfirmed: Boolean = false,
) {

    fun login() {
        lastLoginDate = now().toLocalDateTime(TimeZone.UTC)
    }

    fun logout() {
        lastLogoutDate = now().toLocalDateTime(TimeZone.UTC)
    }
}