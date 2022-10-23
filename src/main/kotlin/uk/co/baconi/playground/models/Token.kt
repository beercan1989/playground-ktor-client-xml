@file:UseSerializers(UuidAsStringSerializer::class)

package uk.co.baconi.playground.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import nl.adaptivity.xmlutil.serialization.XmlValue
import uk.co.baconi.playground.ksx.UuidAsStringSerializer
import java.util.*

@Serializable
@SerialName("token")
data class Token(@XmlValue(true) val value: UUID)
