package com.example.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {

    Database.connect(
        "jdbc:postgresql://localhost:5432/qit_demo",
        user = "postgres",
        password = "abhinav99_pg"
    )

}