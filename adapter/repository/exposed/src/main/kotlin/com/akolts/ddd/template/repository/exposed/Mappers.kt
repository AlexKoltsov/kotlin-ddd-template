package com.akolts.ddd.template.repository.exposed

import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.model.VerificationCode
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder

fun ResultRow.toUser() = User(
    id = this[Users.id],
    email = this[Users.email],
    phone = this[Users.phone],
    password = this[Users.password],
    emailConfirmed = this[Users.emailConfirmed],
    phoneConfirmed = this[Users.phoneConfirmed],
    firstName = this[UserProfiles.firstName],
    lastName = this[UserProfiles.lastName],
    registrationDate = this[SessionInfos.registrationDate],
    lastLoginDate = this[SessionInfos.lastLoginDate],
    lastLogoutDate = this[SessionInfos.lastLogoutDate],
)

fun ResultRow.toVerificationCode() = VerificationCode(
    id = this[VerificationCodes.id],
    userId = this[VerificationCodes.userId],
    code = this[VerificationCodes.code],
)

fun <T> UpdateBuilder<T>.users(user: User) {
    this[Users.id] = user.id
    this[Users.phone] = user.phone
    this[Users.email] = user.email
    this[Users.password] = user.password
    this[Users.emailConfirmed] = user.emailConfirmed
    this[Users.phoneConfirmed] = user.phoneConfirmed
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

fun <T> UpdateBuilder<T>.verificationCodes(verificationCode: VerificationCode) {
    this[VerificationCodes.id] = verificationCode.id
    this[VerificationCodes.userId] = verificationCode.userId
    this[VerificationCodes.code] = verificationCode.code
}