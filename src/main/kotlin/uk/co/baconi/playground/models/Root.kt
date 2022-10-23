package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("root")
data class Root(
    val request: Request? = null,
    val response: Response? = null
)