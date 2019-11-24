package eu.dreamcode.kotlinshell.inject

import io.github.classgraph.ScanResult
import kotlin.reflect.KClass

fun createInstance(scanResult: ScanResult, clazz: KClass<*>) {
    val constructor = findEasiestConstructor(scanResult, clazz)
}

fun findEasiestConstructor(scanResult: ScanResult, clazz: KClass<*>) = clazz.constructors.minBy { it.parameters.size }!!
