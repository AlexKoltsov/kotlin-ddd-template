package com.akolts.ddd.template.repository.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initializeExposed() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/ddd-template",
        driver = "org.postgresql.Driver",
        user = "ddd-template",
        password = "ddd-template"
    )
    transaction {
        SchemaUtils.drop(Users, UserProfiles, SessionInfos, VerificationCodes)
        SchemaUtils.create(Users, UserProfiles, SessionInfos, VerificationCodes)
    }
}