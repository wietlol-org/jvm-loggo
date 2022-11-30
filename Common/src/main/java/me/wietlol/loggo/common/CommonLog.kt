package me.wietlol.loggo.common

import me.wietlol.loggo.api.LogSeverity
import me.wietlol.loggo.api.MeasurableLog
import java.time.Instant
import java.util.*

data class CommonLog(
	override val severity: LogSeverity,
	val moment: Instant,
	val source: LogSource,
	val sequenceId: UUID,
	val eventId: EventId,
	val data: Any,
	val exception: Throwable?,
	val metadata: MutableMap<String, Any>,
) : MeasurableLog

