package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("response")
data class Response(
    val responseOne: ResponseOne? = null,
    val responseTwo: ResponseTwo? = null
)
