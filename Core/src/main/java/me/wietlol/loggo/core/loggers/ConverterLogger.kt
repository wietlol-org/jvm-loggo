package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger

class ConverterLogger<I : Any, O : Any>(
	val logger: Logger<O>,
	val converter: (I) -> O
) : Logger<I>
{
	override fun logAll(logs: Collection<I>)
	{
		logger.logAll(logs.map(converter))
	}
	
	override fun close()
	{
		logger.close()
	}
}
