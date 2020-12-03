package com.go.pushback

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.event.Level

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.pushBack(testing: Boolean = false) {

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        route("/push-back/v1") {
            get("/") {
                call.respond(mapOf("push" to "back"))
            }

            post("/token") {
                val request = call.receive<PushBackTokenPost>()
                println("token = [${request.token.value}]")
                call.respond(mapOf( "token" to request.token))
            }
        }
    }
}



