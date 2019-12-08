package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.inject.InjectionResolver
import eu.dreamcode.kotlinshell.inject.TypeRegistry
import eu.dreamcode.kotlinshell.test.fixtures.injectionresolver.NoArgsConstructorClass
import kotlin.test.*

class InjectionResolverTest {

    private var typeRegistry = TypeRegistry()
    private var injectionResolver = InjectionResolver(this.typeRegistry)

    @BeforeTest
    fun setUp() {
        this.typeRegistry = TypeRegistry()
        this.injectionResolver = InjectionResolver(this.typeRegistry)
    }

    @Test
    fun `Gives no-args-constructor if available`() {
        val resolvedConstructor = this.injectionResolver.findResolvableConstructor(NoArgsConstructorClass::class)

        assertNotNull(resolvedConstructor)
        assertEquals(0, resolvedConstructor.parameters.size)
        assertTrue(NoArgsConstructorClass::class.constructors.contains(resolvedConstructor))
    }

    /*@Test
    fun `resolves simple dependency correctly without priming the type registry`() {
        val resolvedConstructor = this.injectionResolver.findResolvableConstructor(ClassWithSimpleDependency::class)

        this.beanFactory.createInstance(resolvedConstructor)

        assertNotNull(resolvedConstructor)
        assertEquals(0, resolvedConstructor.parameters.size)
        assertTrue(NoArgsConstructorClass::class.constructors.contains(resolvedConstructor))
    }*/

}
