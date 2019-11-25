package eu.dreamcode.kotlinshell.inject

import eu.dreamcode.kotlinshell.exception.InvalidSingletonException
import kotlin.reflect.KClass

internal class TypeRegistry {

    private var singletons: MutableMap<String, Any> = mutableMapOf()
    private var objects: MutableMap<String, Any> = mutableMapOf()

    init {
        this.singletons[TypeRegistry::class.qualifiedName!!] = this
    }

    fun register(identifier: String, obj: Any) {
        require(!this.objects.containsKey(identifier)) {
            "An object with this identifier is already registered. Implicit overriding is not allowed. Please make sure to remove the object first."
        }

        this.objects[identifier] = obj
    }

    fun registerSingleton(obj: Any) {
        requireNotNull(obj::class.qualifiedName) {
            throw InvalidSingletonException("Anonymous classes or object literals can not be registered as a singleton.")
        }

        require(!obj.isString() && !obj.isPrimitive()) {
            throw InvalidSingletonException("Primitives and strings are not allowed to be registered as singletons.")
        }

        this.singletons[obj::class.qualifiedName!!] = obj
    }

    fun get(identifier: String): Any? = this.objects[identifier] ?: this.singletons[identifier]

    fun get(clazz: KClass<*>): Any? = this.singletons[clazz.qualifiedName]

}

internal fun Any.isString(): Boolean = this is String
internal fun Any.isPrimitive(): Boolean = this::class.javaPrimitiveType?.let { true } ?: false

