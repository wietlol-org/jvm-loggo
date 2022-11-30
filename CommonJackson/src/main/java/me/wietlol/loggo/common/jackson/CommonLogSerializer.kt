package me.wietlol.loggo.common.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import me.wietlol.loggo.common.CommonLog
import java.io.PrintWriter
import java.io.StringWriter
import java.time.format.DateTimeFormatter

class CommonLogSerializer(t: Class<CommonLog>? = null) : StdSerializer<CommonLog>(t)
{
	override fun serialize(value: CommonLog, gen: JsonGenerator, provider: SerializerProvider)
	{
		gen.apply {
			writeStartObject()
			
			writeObjectField("severity", value.severity)
			writeObjectField("moment", value.moment.let { DateTimeFormatter.ISO_INSTANT.format(it) })
			writeObjectField("source", value.source)
			writeObjectField("sequenceId", value.sequenceId)
			writeObjectField("eventId", value.eventId)
			writeObjectField("data", value.data)
			writeObjectField("exception", value.exception)
			writeObjectField("exceptionMessage", value.exception?.let {
				StringWriter().use { sw ->
					PrintWriter(sw).use { pw ->
						it.printStackTrace(pw)
					}
					sw.toString()
				}
			})
			
			writeEndObject()
		}
	}
}
