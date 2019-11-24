package eu.dreamcode.kotlinshell.test.utility

import eu.dreamcode.kotlinshell.ComponentScanner
import io.github.classgraph.ClassGraph

internal class Global {

    companion object {
        val scanResult = ClassGraph()
            .whitelistPackages("eu.dreamcode.kotlinshell.test")
            .enableClassInfo()
            .enableMethodInfo()
            .enableAnnotationInfo()
            .scan()

        val scanner = ComponentScanner(
            this.scanResult
        )
    }

}
