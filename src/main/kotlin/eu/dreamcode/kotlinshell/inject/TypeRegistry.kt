package eu.dreamcode.kotlinshell.inject

import eu.dreamcode.kotlinshell.exception.InvalidSingletonException
import kotlin.reflect.KClass

internal class TypeRegistry {

    private var singletons: MutableMap<String, Any> = mutableMapOf()
    private var objects: MutableMap<String, Any> = mutableMapOf()

    fun register(identifier: String, obj: Any) {
        /*require(!this.objects.containsKey(identifier)) {
            "An object with this identifier is already registered. Implicit overriding is not allowed. Please make sure to remove the object first."
        }*/

        this.objects[identifier] = obj
    }

    fun registerSingleton(obj: Any) {
        /*requireNotNull(obj::class.qualifiedName) {
            throw InvalidSingletonException("Anonymous classes or object literals can not be registered as a singleton.")
        }*/

        this.singletons[obj::class.qualifiedName!!] = obj
    }

    fun get(identifier: String): Pair<Any, BeanType>? = this.objects[identifier]?.let { Pair(it, BeanType.OBJECT) }
        ?: this.singletons[identifier]?.let { Pair(it, BeanType.SINGLETON) }

    fun get(clazz: KClass<*>): Pair<Any, BeanType>? = this.singletons[clazz.qualifiedName]?.let { Pair(it, BeanType.SINGLETON) }

}
