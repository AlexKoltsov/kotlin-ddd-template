package com.akolts.ddd.template.repository.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun initializeExposed() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/ddd-template",
        driver = "org.postgresql.Driver",
        user = "ddd-template",
        password = "ddd-template"
    )
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.drop(Users, UserProfiles, SessionInfos)
        SchemaUtils.create(Users, UserProfiles, SessionInfos)
    }
}