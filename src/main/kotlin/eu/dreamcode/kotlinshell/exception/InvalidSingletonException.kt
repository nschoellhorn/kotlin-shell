package eu.dreamcode.kotlinshell.exception

class InvalidSingletonException(message: String = "Primitives and Strings are not allowed to be registered as a singleton")
    : RuntimeException(message)
