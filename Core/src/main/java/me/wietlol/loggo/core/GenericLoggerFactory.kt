package me.wietlol.loggo.core

import me.wietlol.loggo.api.Logger
import me.wietlol.loggo.api.LoggerFactory

class GenericLoggerFactory<in T : Any>(
	val factory: () -> Logger<T>
) : LoggerFactory<T>
{
	override fun createLogger(): Logger<T> =
		factory()
}
