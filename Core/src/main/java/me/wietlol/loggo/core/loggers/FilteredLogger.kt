package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger
import java.util.*

class FilteredLogger<in T : Any>(
	val logger: Logger<T>,
	filter: LogFilter.(T) -> Boolean
) : Logger<T>
{
	private val filter = object : LogFilter
	{
		fun test(log: T): Boolean =
			filter(log)
		
		override fun filterRulesChanged() =
			processFilteredLogs()
	}
	
	private val filteredLogs: MutableList<T> = LinkedList()
	
	override fun logAll(logs: Collection<T>)
	{
		val (passed, filtered) = logs.partition(filter::test)
		filteredLogs.addAll(filtered)
		logger.logAll(passed)
	}
	
	private fun processFilteredLogs()
	{
		with(filteredLogs.listIterator()) {
			generateSequence { true }
				.takeWhile { hasNext() }
				.map { next() }
				.filter { filter.test(it) }
				.onEach { remove() }
				.forEach { logger.log(it) }
		}
	}
	
	override fun close()
	{
		logger.close()
	}
	
	interface LogFilter
	{
		fun filterRulesChanged()
	}
}
