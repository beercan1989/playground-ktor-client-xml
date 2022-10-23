package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@SerialName("username")
data class Username(@XmlValue(true) val value: String)
