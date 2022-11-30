package me.wietlol.loggo.core

import me.wietlol.loggo.api.Logger
import me.wietlol.loggo.api.LoggerFactory
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

class CachingLoggerFactory<T : Any>(
	val factory: LoggerFactory<T>
) : LoggerFactory<T>
{
	private val loggerCache: BlockingDeque<Logger<T>> = LinkedBlockingDeque()
	
	override fun createLogger(): Logger<T> =
		getLogger()
	
	private fun getLogger(): Logger<T> =
		ProxyCachedLogger(this)
	
	private fun pushCachedLogger(logger: Logger<T>) =
		logger.also {
			loggerCache.push(logger)
		}
	
	private fun getOrCreateLogger(): Logger<T> =
		peekCachedLogger()
			?: pushCachedLogger(factory.createLogger())
	
	private fun peekCachedLogger(): Logger<T>? =
		loggerCache
			.let { runCatching { it.peek() } }
			.getOrNull()
	
	private fun popCachedLogger()
	{
		loggerCache
			.also { runCatching { it.pop() } }
	}
	
	private class ProxyCachedLogger<T : Any>(
		val factory: CachingLoggerFactory<T>
	) : Logger<T>
	{
		val logger: Logger<T>
			get() = factory.getOrCreateLogger()
		
		override fun logAll(logs: Collection<T>) =
			logger.logAll(logs)
		
		override fun close()
		{
			factory.popCachedLogger()
			logger.close()
		}
	}
}
