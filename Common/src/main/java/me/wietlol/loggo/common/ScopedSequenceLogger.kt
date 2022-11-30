package me.wietlol.loggo.common

import java.util.*

class ScopedSequenceLogger(
	val logger: CommonLogger,
	val replace: (UUID) -> UUID
) : CommonLogger
{
	override fun log(log: CommonLog)
	{
		logger.log(log.copy(
			sequenceId = replace(log.sequenceId)
		))
	}
	
	override fun logAll(logs: Collection<CommonLog>)
	{
		logger.logAll(logs.map {
			it.copy(
				sequenceId = replace(it.sequenceId)
			)
		})
	}
	
	override fun close()
	{
		logger.close()
	}
}
