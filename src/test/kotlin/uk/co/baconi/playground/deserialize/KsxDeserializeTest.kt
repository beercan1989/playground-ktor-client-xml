package uk.co.baconi.playground.deserialize

import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import uk.co.baconi.playground.createMockHttpClient
import uk.co.baconi.playground.models.Root
import java.util.*

class KsxDeserializeTest {

    @Test
    fun `should deserialize root with response for response one`(): Unit = runBlocking {

        val httpClient = createMockHttpClient {
            respond(
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/xml"),
                content = """
                    |<root>
                    |   <response>
                    |       <responseOne>
                    |           <token>17860f84-ef37-4f89-b1ab-24e1852c28d4</token>
                    |           <username>aardvark</username>
                    |       </responseOne>
                    |   </response>
                    |</root>""".trimMargin()
            )
        }

        val result = httpClient.post("http://localhost")
        result.status shouldBe HttpStatusCode.OK

        val root = result.body<Root>()
        root.request should beNull()

        val response = root.response.shouldNotBeNull()
        response.responseTwo should beNull()

        val responseOne = response.responseOne.shouldNotBeNull()
        responseOne.token shouldBe UUID.fromString("17860f84-ef37-4f89-b1ab-24e1852c28d4")
        responseOne.username shouldBe "aardvark"
    }

    @Test
    fun `should deserialize root with response for response two`(): Unit = runBlocking {

        val httpClient = createMockHttpClient {
            respond(
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/xml"),
                content = """
                    |<root>
                    |   <response>
                    |       <responseTwo>
                    |           <token>17860f84-ef37-4f89-b1ab-24e1852c28d4</token>
                    |           <username>aardvark</username>
                    |       </responseTwo>
                    |   </response>
                    |</root>""".trimMargin()
            )
        }

        val result = httpClient.post("http://localhost")
        result.status shouldBe HttpStatusCode.OK

        val root = result.body<Root>()
        root.request should beNull()

        val response = root.response.shouldNotBeNull()
        response.responseOne should beNull()

        val responseTwo = response.responseTwo.shouldNotBeNull()
        responseTwo.token.value shouldBe UUID.fromString("17860f84-ef37-4f89-b1ab-24e1852c28d4")
        responseTwo.username.value shouldBe "aardvark"
    }
}