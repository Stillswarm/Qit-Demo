package com.example.routes

import com.example.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRoutes(UserRepository())

    }
}

