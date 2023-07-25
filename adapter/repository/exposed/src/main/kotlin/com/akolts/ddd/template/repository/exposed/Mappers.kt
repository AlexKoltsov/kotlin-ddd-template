package com.akolts.ddd.template.repository.exposed

import com.akolts.ddd.template.domain.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder

fun ResultRow.toDomain() = User(
    id = this[Users.id],
    email = this[Users.email],
    phone = this[Users.phone],
    password = this[Users.password],
    firstName = this[UserProfiles.firstName],
    lastName = this[UserProfiles.lastName],
    registrationDate = this[SessionInfos.registrationDate],
    lastLoginDate = this[SessionInfos.lastLoginDate],
)

fun <T> UpdateBuilder<T>.fromDomain(user: User) {
    this[Users.id] = user.id
    this[Users.phone] = user.phone
    this[Users.email] = user.email
    this[Users.password] = user.password
    this[UserProfiles.firstName] = user.firstName
    this[UserProfiles.lastName] = user.lastName
    this[SessionInfos.lastLoginDate] = user.lastLoginDate
    this[SessionInfos.registrationDate] = user.registrationDate
}