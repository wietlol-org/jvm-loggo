package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.write

class LazyLogger<in T : Any>(
	val loggerSupplier: () -> Logger<T>
) : Logger<T>
{
	private lateinit var logger: Logger<T>
	private val lock = ReentrantReadWriteLock()
	
	override fun logAll(logs: Collection<T>)
	{
		if (::logger.isInitialized.not())
			lock.write {
				if (::logger.isInitialized.not())
					logger = loggerSupplier()
			}
		
		logger.logAll(logs)
	}
	
	override fun close()
	{
		if (::logger.isInitialized)
			logger.close()
	}
}
