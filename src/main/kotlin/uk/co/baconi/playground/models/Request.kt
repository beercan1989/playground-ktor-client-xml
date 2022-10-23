package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("request")
data class Request(
    val authentication: Authentication,
    val requestOne: RequestOne? = null,
    val requestTwo: RequestTwo? = null,
)
