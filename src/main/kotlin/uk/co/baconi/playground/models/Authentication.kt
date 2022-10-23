package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement

@Serializable
@SerialName("authentication")
data class Authentication(@XmlElement(true) val username: String, @XmlElement(true) val password: String)
