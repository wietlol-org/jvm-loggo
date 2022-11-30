package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger

class NoOpLogger<in T : Any> : Logger<T>
{
	override fun logAll(logs: Collection<T>)
	{
		// nothing to do
	}
	
	override fun close()
	{
		// nothing to do
	}
}
