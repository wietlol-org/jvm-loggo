package me.wietlol.loggo.core

import me.wietlol.loggo.api.Logger
import me.wietlol.loggo.api.LoggerFactory

/*
* createLogger() will create a proxy logger where each instance (*1) will use the same backing logger
* when a logger is closed, it will reset the backing logger to be recreated when new log calls are made
*
* *1 each instance made by the instance of the SingletonLoggerFactory
* */
class SingletonLoggerFactory<T : Any>(
	val factory: LoggerFactory<T>
) : LoggerFactory<T>
{
	private var logger: Logger<T>? = null
	
	override fun createLogger(): Logger<T> =
		ProxySingletonLogger(this)
	
	private fun getLogger(): Logger<T>
	{
		if (logger == null)
			logger = factory.createLogger()
		return logger!!
	}
	
	private class ProxySingletonLogger<T : Any>(
		val factory: SingletonLoggerFactory<T>
	) : Logger<T>
	{
		override fun logAll(logs: Collection<T>)
		{
			factory.getLogger().logAll(logs)
		}
		
		override fun close()
		{
			factory.getLogger().close()
			factory.logger = null
		}
	}
}
