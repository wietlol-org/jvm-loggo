package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger
import java.time.Duration
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread

class BackgroundLogger<T : Any>(
	val backingLogger: Logger<T>,
	interval: Duration
) : Logger<T>
{
	private val queue: Queue<T> = ConcurrentLinkedQueue()
	private var isRunning = true
	
	init
	{
		thread {
			val intervalMillis = interval.toMillis()
			while (isRunning) {
				passLogs()
				Thread.sleep(intervalMillis)
			}
			
			passLogs()
			backingLogger.close()
		}
	}
	
	private fun passLogs()
	{
		val logs: List<T> = generateSequence { queue.poll() }.toList()
		if (logs.isNotEmpty())
			backingLogger.logAll(logs)
	}
	
	override fun logAll(logs: Collection<T>)
	{
		queue.addAll(logs)
	}
	
	override fun close()
	{
		isRunning = false
	}
}
