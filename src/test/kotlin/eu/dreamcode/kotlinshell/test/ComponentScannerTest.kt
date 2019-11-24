package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.ComponentScanner
import eu.dreamcode.kotlinshell.test.fixtures.ShellComponentFixture
import eu.dreamcode.kotlinshell.test.utility.Global
import io.github.classgraph.ClassGraph
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentScannerTest {

    @Test
    fun `Scanner finds classes annotated with @ShellComponent`() {
        assertEquals(listOf(ShellComponentFixture::class), Global.scanner.findShellComponents())
    }

}
