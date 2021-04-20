package com.go.pushback

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.go.pushback.model.testData
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.request.path
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.slf4j.event.Level


private val logger = KotlinLogging.logger {}

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

        logApplicationInfo()

        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        route("/push-back/v1") {
            get("/") {
                logThread()
                call.respond(mapOf("push" to "back"))
                launch {
                    logThread()
                }
            }

            get("/settings/fcm") {
                call.respond(testData())
            }

            get("/settings/hms") {
                call.respond(testData())
            }

            post("/token") {
                val request = call.receive<PushBackTokenPost>()
                println("token = [${request.token.value}]")
                call.respond(mapOf("token" to request.token))
            }
        }
    }
}

fun logApplicationInfo(): Unit {
    logger.info {  Runtime.version() }
}

fun logThread(): Unit {
    logger.info { Thread.currentThread() }
}