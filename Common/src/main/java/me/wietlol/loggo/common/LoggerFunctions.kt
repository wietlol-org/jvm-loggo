package me.wietlol.loggo.common

import me.wietlol.loggo.api.LogSeverity
import java.time.Instant
import java.util.*

private val defaultSource = LogSource(emptyList())
private val defaultSequenceId = UUID(0, 0)

fun CommonLogger.log(severity: LogSeverity, eventId: EventId, data: Any, exception: Throwable? = null, metadata: MutableMap<String, Any>) =
	log(CommonLog(
		severity,
		Instant.now(),
		defaultSource,
		defaultSequenceId,
		eventId,
		data,
		exception,
		metadata,
	))

fun CommonLogger.logTrace(eventId: EventId, data: Any, exception: Throwable? = null, metadata: MutableMap<String, Any>) =
	log(trace, eventId, data, exception, metadata)

fun CommonLogger.logDebug(eventId: EventId, data: Any, exception: Throwable? = null, metadata: MutableMap<String, Any>) =
	log(debug, eventId, data, exception, metadata)

fun CommonLogger.logInformation(eventId: EventId, data: Any, exception: Throwable? = null, metadata: MutableMap<String, Any>) =
	log(information, eventId, data, exception, metadata)

fun CommonLogger.logWarning(eventId: EventId, data: Any, exception: Throwable? = null, metadata: MutableMap<String, Any>) =
	log(warning, eventId, data, exception, metadata)

fun CommonLogger.logError(eventId: EventId, data: Any, exception: Throwable?, metadata: MutableMap<String, Any>) =
	log(error, eventId, data, exception, metadata)

fun CommonLogger.logCritical(eventId: EventId, data: Any, exception: Throwable?, metadata: MutableMap<String, Any>) =
	log(critical, eventId, data, exception, metadata)
