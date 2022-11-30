package me.wietlol.loggo.common

data class LogSource(
	val names: List<String>
)
{
	operator fun plus(name: String): LogSource =
		LogSource(names + name)
	
	operator fun plus(names: List<String>): LogSource =
		LogSource(this.names + names)
	
	operator fun plus(source: LogSource): LogSource =
		LogSource(names + source.names)
}
