package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.FlushableLogger
import me.wietlol.loggo.api.Logger
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

class AutoBulkLogger<in T : Any>(
	val logger: Logger<T>,
	val maxLogCount: Int
) : FlushableLogger<T>
{
	private val queue: Queue<T> = ConcurrentLinkedQueue()
	
	override fun logAll(logs: Collection<T>)
	{
		queue.addAll(logs)
		
		if (queue.size > maxLogCount)
			flush()
	}
	
	override fun flush()
	{
		generateSequence { queue.poll() }
			.forEach { logger.log(it) }
	}
	
	override fun close()
	{
		flush()
		logger.close()
	}
}
