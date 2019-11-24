package eu.dreamcode.kotlinshell

import io.github.classgraph.ClassInfo
import io.github.classgraph.ScanResult
import kotlin.reflect.KClass

internal class ComponentScanner(private val scanResult: ScanResult) {

    fun findShellComponents() = this.scanResult.getClassesWithAnnotation(ShellComponent::class.qualifiedName)
        .map { it.loadClass().kotlin }

}
