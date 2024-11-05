package com.example

import com.example.plugins.*
import com.example.routes.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureDatabases()
    configureSockets()
    configureRouting()
}
