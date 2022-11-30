package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger

class GenericLogger<T : Any>(
	val logFunction: (Collection<T>) -> Unit,
	val closeFunction: (() -> Unit)? = null
) : Logger<T>
{
	override fun logAll(logs: Collection<T>)
	{
		logFunction(logs)
	}
	
	override fun close()
	{
		closeFunction?.invoke()
	}
}
