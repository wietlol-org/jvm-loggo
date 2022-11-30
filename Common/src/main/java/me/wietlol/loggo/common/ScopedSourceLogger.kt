package me.wietlol.loggo.common

class ScopedSourceLogger(
	val logger: CommonLogger,
	val replace: (LogSource) -> LogSource
) : CommonLogger
{
	override fun log(log: CommonLog)
	{
		logger.log(log.copy(
			source = replace(log.source)
		))
	}
	
	override fun logAll(logs: Collection<CommonLog>)
	{
		logger.logAll(logs.map {
			it.copy(
				source = replace(it.source)
			)
		})
	}
	
	override fun close()
	{
		logger.close()
	}
}
