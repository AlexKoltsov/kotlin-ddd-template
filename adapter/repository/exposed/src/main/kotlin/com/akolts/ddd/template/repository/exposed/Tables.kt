package com.akolts.ddd.template.repository.exposed

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

object Users : Table(name = "user") {
    val id: Column<UUID> = uuid("id")
    val email: Column<String?> = varchar("email", length = 256).nullable()
    val phone: Column<String?> = varchar("phone", length = 256).nullable()
    val password: Column<String> = varchar("password", length = 256)
    val emailConfirmed: Column<Boolean> = bool("email_confirmed")
    val phoneConfirmed: Column<Boolean> = bool("phone_confirmed")

    override val primaryKey = PrimaryKey(id, name = "user_id_pkey")
}

object UserProfiles : Table(name = "user_profile") {
    val userId: Column<UUID> = reference("user_id", Users.id)

    val firstName: Column<String?> = varchar("first_name", length = 256).nullable()
    val lastName: Column<String?> = varchar("last_name", length = 256).nullable()
}

object SessionInfos : Table(name = "session_info") {
    val userId: Column<UUID> = reference("user_id", Users.id)

    val registrationDate: Column<LocalDateTime> = datetime("registration_date")
    val lastLoginDate: Column<LocalDateTime?> = datetime("last_login_date").nullable()
    val lastLogoutDate: Column<LocalDateTime?> = datetime("last_logout_date").nullable()
}

object VerificationCodes : Table(name = "verification_code") {
    val id: Column<UUID> = uuid("id")
    val userId: Column<UUID> = reference("user_id", Users.id)
    val code: Column<String> = varchar("code", length = 256)

    override val primaryKey = PrimaryKey(Users.id, name = "verification_code_id_pkey")
}
