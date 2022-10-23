@file:UseSerializers(UuidAsStringSerializer::class)

package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import nl.adaptivity.xmlutil.serialization.XmlElement
import uk.co.baconi.playground.ksx.UuidAsStringSerializer
import java.util.*

@Serializable
@SerialName("responseOne")
data class ResponseOne(@XmlElement(true) val token: UUID, @XmlElement(true) val username: String)
