package com.example.routes

import com.example.model.User
import com.example.repository.UserRepository
import com.example.repository.Users
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.userRoutes(userRepository: UserRepository) {

    transaction {
        SchemaUtils.create(Users)
    }

    route("/user") {
        post("/create") {
            val newUser = call.receive<User>()
            val id = userRepository.createUser(newUser)
            call.respondText(id.toString())
        }

        get("/{id}") {
            val userId = call.parameters["id"]
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val user = userRepository.getUserDetails(userId.toInt())
            call.respond(user ?: HttpStatusCode.NotFound)
        }

        delete("/delete/{id}") {
            val userId = call.parameters["id"]
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            userRepository.deleteUser(userId.toInt())
        }
    }
}
