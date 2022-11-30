package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger

class MultiLogger<in T : Any>(
	val loggers: List<Logger<T>>
) : Logger<T>
{
	override fun logAll(logs: Collection<T>)
	{
		loggers.forEach { it.logAll(logs) }
	}
	
	override fun close()
	{
		loggers.forEach { it.close() }
	}
}
