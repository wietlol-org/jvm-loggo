package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger

class ConsoleLogger<in T : CharSequence> : Logger<T>
{
	override fun logAll(logs: Collection<T>)
	{
		logs.forEach(::println)
	}
	
	override fun close()
	{
		// nothing to do
	}
}
