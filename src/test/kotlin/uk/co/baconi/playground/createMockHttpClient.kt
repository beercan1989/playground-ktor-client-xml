package uk.co.baconi.playground

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.xml.*

fun createMockHttpClient(handler: MockRequestHandler): HttpClient = HttpClient(MockEngine) {
    install(Logging) {
        level = LogLevel.ALL
    }
    install(ContentNegotiation) {
        xml()
    }
    engine {
        addHandler(handler)
    }
    defaultRequest {
        contentType(ContentType.Application.Xml)
    }
}
