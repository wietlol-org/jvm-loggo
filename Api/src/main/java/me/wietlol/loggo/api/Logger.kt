package me.wietlol.loggo.api

import java.io.Closeable

interface Logger<in T : Any> : Closeable
{
	fun log(log: T) =
		logAll(listOf(log))
	
	fun logAll(logs: Collection<T>)
}
