package uk.co.baconi.playground.serialize

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import uk.co.baconi.playground.createMockHttpClient
import uk.co.baconi.playground.models.*

class KsxSerializeTest {

    private var captured: String? = null

    private val httpClient = createMockHttpClient { request ->
        captured = request.body.toByteArray().toString(Charsets.UTF_8)
        respondOk("<success />")
    }

    @Test
    fun `should serialize root with request for request one`(): Unit = runBlocking {

        val response = httpClient.post {
            setBody(
                Root(
                    request = Request(
                        authentication = Authentication(username = "system-username", password = "system-password"),
                        requestOne = RequestOne(username = "aardvark", password = "P@55w0rd")
                    )
                )
            )
        }

        response.status shouldBe HttpStatusCode.OK
        response.bodyAsText() shouldBe "<success />"

        captured.shouldNotBeNull()
        captured shouldBe "<root>" +
                "<request>" +
                "<authentication>" +
                "<username>system-username</username>" +
                "<password>system-password</password>" +
                "</authentication>" +
                "<requestOne>" +
                "<username>aardvark</username>" +
                "<password>P@55w0rd</password>" +
                "</requestOne>" +
                "</request>" +
                "</root>"
    }

    @Test
    fun `should serialize root with request for request two`(): Unit = runBlocking {

        val response = httpClient.post {
            setBody(
                Root(
                    request = Request(
                        authentication = Authentication(username = "badger", password = "mash potatoes"),
                        requestTwo = RequestTwo(username = Username("aardvark"))
                    )
                )
            )
        }

        response.status shouldBe HttpStatusCode.OK
        response.bodyAsText() shouldBe "<success />"

        captured.shouldNotBeNull()
        captured shouldBe "<root>" +
                "<request>" +
                "<authentication>" +
                "<username>badger</username>" +
                "<password>mash potatoes</password>" +
                "</authentication>" +
                "<requestTwo>" +
                "<username>aardvark</username>" +
                "</requestTwo>" +
                "</request>" +
                "</root>"
    }
}