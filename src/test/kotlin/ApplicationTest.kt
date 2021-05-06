package com.go.pushback

import io.ktor.http.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import junit.framework.Assert.assertEquals
import org.junit.Test

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ pushBack(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }
}
