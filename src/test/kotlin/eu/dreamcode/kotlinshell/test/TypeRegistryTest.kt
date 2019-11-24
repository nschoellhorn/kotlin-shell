package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.exception.InvalidSingletonException
import eu.dreamcode.kotlinshell.inject.BeanType
import eu.dreamcode.kotlinshell.inject.TypeRegistry
import eu.dreamcode.kotlinshell.test.fixtures.SingletonFixture
import java.lang.reflect.Type
import kotlin.test.*

class TypeRegistryTest {

    private var typeRegistry: TypeRegistry = TypeRegistry()

    @BeforeTest
    fun setUp() {
        this.typeRegistry = TypeRegistry()
    }

    @Test
    fun `get() returns tuple with object and correct source type`() {
        val testObject = 5
        val testSingleton = SingletonFixture()

        typeRegistry.register("testObject", 5)
        this.typeRegistry.registerSingleton(testSingleton)

        assertEquals(testObject to BeanType.OBJECT, this.typeRegistry.get("testObject"))
        assertEquals(testSingleton to BeanType.SINGLETON, this.typeRegistry.get(SingletonFixture::class))
        assertEquals(testSingleton to BeanType.SINGLETON, this.typeRegistry.get(SingletonFixture::class.qualifiedName!!))
    }

    @Test
    fun `Returns null when no match is found in registry`() {
        assertNull(this.typeRegistry.get(SingletonFixture::class))
    }

    @Test
    fun `registerSingleton() denies registering primitives and strings as singleton`() {
        assertFailsWithInvalidSingletonException { this.typeRegistry.registerSingleton(5) }
        assertFailsWithInvalidSingletonException { this.typeRegistry.registerSingleton(5.0) }
        assertFailsWithInvalidSingletonException { this.typeRegistry.registerSingleton('5') }
        assertFailsWithInvalidSingletonException { this.typeRegistry.registerSingleton("5") }
        assertFailsWithInvalidSingletonException { this.typeRegistry.registerSingleton(true) }
    }

    private inline fun assertFailsWithInvalidSingletonException(lambda: () -> Unit) {
        assertFailsWith(InvalidSingletonException::class, lambda)
    }

}
