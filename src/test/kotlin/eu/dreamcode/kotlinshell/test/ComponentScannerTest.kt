package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.test.fixtures.typeregistry.ShellComponentFixture
import eu.dreamcode.kotlinshell.test.utility.Global
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentScannerTest {

    @Test
    fun `Scanner finds classes annotated with @ShellComponent`() {
        assertEquals(listOf(ShellComponentFixture::class), Global.scanner.findShellComponents())
    }

}
