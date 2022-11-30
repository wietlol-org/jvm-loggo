package me.wietlol.loggo.common

import me.wietlol.loggo.api.LogSeverity

private data class CommonLogSeverity(
	override val name: String,
	override val value: Double
) : LogSeverity

val critical: LogSeverity = CommonLogSeverity("Critical", 6.0)
val error: LogSeverity = CommonLogSeverity("Error", 5.0)
val warning: LogSeverity = CommonLogSeverity("Warning", 4.0)
val information: LogSeverity = CommonLogSeverity("Information", 3.0)
val debug: LogSeverity = CommonLogSeverity("Debug", 2.0)
val trace: LogSeverity = CommonLogSeverity("Trace", 1.0)
