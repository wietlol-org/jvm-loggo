package me.wietlol.loggo.api

interface FlushableLogger<in T : Any> : Logger<T>
{
	fun flush()
}
