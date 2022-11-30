package me.wietlol.loggo.core

import me.wietlol.loggo.api.LogSeverity
import me.wietlol.loggo.api.MeasurableLog
import me.wietlol.loggo.core.loggers.FilteredLogger.LogFilter

class TriggerableLevelFilter<T : MeasurableLog>(
	startLevel: Double,
	val triggerLevel: Double,
	val targetLevel: Double
)
{
	private var currentLevel = startLevel
	
	fun apply(filter: LogFilter, log: T): Boolean
	{
		if (log.severity.value >= triggerLevel && targetLevel < currentLevel)
		{
			currentLevel = targetLevel
			filter.filterRulesChanged()
		}
		
		return log.severity.value >= currentLevel
	}
	
	companion object
	{
		operator fun <T : MeasurableLog> invoke(
			startLevel: LogSeverity,
			triggerLevel: LogSeverity,
			targetLevel: LogSeverity
		): TriggerableLevelFilter<T> =
			TriggerableLevelFilter(
				startLevel.value,
				triggerLevel.value,
				targetLevel.value
			)
	}
}
