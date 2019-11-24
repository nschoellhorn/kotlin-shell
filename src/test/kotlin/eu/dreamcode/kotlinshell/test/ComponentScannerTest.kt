package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.ComponentScanner
import eu.dreamcode.kotlinshell.test.fixtures.ShellComponentFixture
import io.github.classgraph.ClassGraph
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentScannerTest {

    private val scanner = ComponentScanner(
        ClassGraph()
            .whitelistPackages("eu.dreamcode.kotlinshell.test")
            .enableClassInfo()
            .enableMethodInfo()
            .enableAnnotationInfo()
            .scan()
    )

    @Test
    fun `Scanner finds classes annotated with @ShellComponent`() {
        assertEquals(listOf(ShellComponentFixture::class), scanner.findShellComponents())
    }

}
