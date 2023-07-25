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
    lastLogoutDate = this[SessionInfos.lastLogoutDate],
)

fun <T> UpdateBuilder<T>.users(user: User) {
    this[Users.id] = user.id
    this[Users.phone] = user.phone
    this[Users.email] = user.email
    this[Users.password] = user.password
}

fun <T> UpdateBuilder<T>.userProfiles(user: User) {
    this[UserProfiles.userId] = user.id
    this[UserProfiles.firstName] = user.firstName
    this[UserProfiles.lastName] = user.lastName
}

fun <T> UpdateBuilder<T>.sessionInfos(user: User) {
    this[SessionInfos.userId] = user.id
    this[SessionInfos.lastLoginDate] = user.lastLoginDate
    this[SessionInfos.lastLogoutDate] = user.lastLogoutDate
    this[SessionInfos.registrationDate] = user.registrationDate
}