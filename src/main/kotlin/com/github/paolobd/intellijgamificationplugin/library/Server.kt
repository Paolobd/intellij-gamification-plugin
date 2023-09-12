package com.github.paolobd.intellijgamificationplugin.library

import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.engine.*
import io.ktor.server.request.*

class Server {
    private val server = embeddedServer(Netty, 8080, module = Application::module)

    fun start() {
        server.start(wait = false)
    }

    fun stop() {
        server.stop()
        events.clear()
    }

    companion object{
        var events = mutableListOf<Event>()
    }
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson()
    }
    routing {
        get("/") {
            val responseData = "Hello, World!"
            call.respond(responseData)
        }
        post("/receiveJson") {
            val data = call.receive<List<Event>>()
            println(data)
            Server.events = data.toMutableList()
            call.respond(HttpStatusCode.OK)
        }
    }
}