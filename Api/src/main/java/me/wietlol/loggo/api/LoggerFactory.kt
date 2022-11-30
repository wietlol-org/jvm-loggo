package me.wietlol.loggo.api

interface LoggerFactory<in T : Any>
{
	fun createLogger(): Logger<T>
}
