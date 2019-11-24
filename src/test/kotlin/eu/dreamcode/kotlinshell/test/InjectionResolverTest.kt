package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.inject.findEasiestConstructor
import eu.dreamcode.kotlinshell.test.fixtures.MultiConstructorClassFixture
import eu.dreamcode.kotlinshell.test.utility.Global
import kotlin.reflect.jvm.jvmErasure
import kotlin.test.Test
import kotlin.test.assertEquals

class InjectionResolverTest {

    @Test
    fun `Constructor with least parameters is found as easiest constructor`() {
        val constructor = findEasiestConstructor(Global.scanResult, MultiConstructorClassFixture::class)
        assertEquals(1, constructor.parameters.size)
        assertEquals(Int::class, constructor.parameters[0].type.jvmErasure)
    }

}
