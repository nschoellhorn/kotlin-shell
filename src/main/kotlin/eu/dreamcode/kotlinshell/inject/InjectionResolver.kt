package eu.dreamcode.kotlinshell.inject

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

internal class InjectionResolver(
    val typeRegistry: TypeRegistry
) {
    internal fun <T : Any> findResolvableConstructor(clazz: KClass<T>): KFunction<T>? {
        val constructor = this.findEasiestConstructor(clazz)

        return null
    }

    /*internal fun <T : Any> createInstance(clazz: KClass<T>): T {
        val constructor = this.findEasiestConstructor(clazz)
    }*/

    /*
     * Simply choose the constructor with the least parameters. This can lead to problems later since it is a very primitive
     * way of defining "easy". There can be cases where we have all the beans required for a 2-arg-constructor while not
     * having the right bean for a 1-arg-constructor so resolution will fail up the chain, but that's to be fixed at a later
     * point.
     */
    private fun <T : Any> findEasiestConstructor(clazz: KClass<T>) =
        clazz.constructors.minBy { it.parameters.size }!!
}




