package me.wietlol.loggo.core.loggers

import me.wietlol.loggo.api.Logger
import java.io.File

class FileLogger<in T : CharSequence>(
	val fileProvider: () -> File
) : Logger<T>
{
	override fun logAll(logs: Collection<T>)
	{
		fileProvider()
			.also { it.parentFile.mkdirs() }
			.appendText(logs.joinToString("\n") + "\n")
	}
	
	override fun close()
	{
		// nothing to do
	}
}
