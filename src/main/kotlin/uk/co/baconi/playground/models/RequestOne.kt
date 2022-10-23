package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

@Serializable
@SerialName("requestOne")
data class RequestOne(@XmlElement(true) val username: String, @XmlElement(true) val password: String)
