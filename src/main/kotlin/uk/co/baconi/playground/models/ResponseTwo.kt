package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("responseTwo")
data class ResponseTwo(val token: Token, val username: Username)
