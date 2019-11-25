package eu.dreamcode.kotlinshell.test

import eu.dreamcode.kotlinshell.exception.InvalidSingletonException
import eu.dreamcode.kotlinshell.inject.TypeRegistry
import eu.dreamcode.kotlinshell.inject.isPrimitive
import eu.dreamcode.kotlinshell.inject.isString
import eu.dreamcode.kotlinshell.test.fixtures.SingletonFixture
import kotlin.test.*

class TypeRegistryTest {

    private var typeRegistry: TypeRegistry = TypeRegistry()

    @BeforeTest
    fun setUp() {
        this.typeRegistry = TypeRegistry()
    }

    @Test
    fun `register() denies implicitly overriding already existing beans`() {
        val fixture = SingletonFixture()
        typeRegistry.register("test", fixture)

        assertFailsWith(IllegalArgumentException::class) {
            typeRegistry.register("test", "test")
        }
        assertEquals(fixture, typeRegistry.get("test") as Any)
    }

    @Test
    fun `registerSingleton() does not allow registering object literals`() {
        val testObject = object {}

        assertFailsWith(InvalidSingletonException::class) {
            typeRegistry.registerSingleton(testObject)
        }
    }

    @Test
    fun `get() returns tuple with object and correct source type`() {
        val testObject = 5
        val testSingleton = SingletonFixture()

        typeRegistry.register("testObject", 5)
        this.typeRegistry.registerSingleton(testSingleton)

        assertEquals(testObject, this.typeRegistry.get("testObject"))
        assertEquals(testSingleton, this.typeRegistry.get(SingletonFixture::class))
        assertEquals(testSingleton, this.typeRegistry.get(SingletonFixture::class.qualifiedName!!))
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

    @Test
    fun `isNotString() detects strings`() {
        val javaString = java.lang.String("")
        val kotlinString = ""
        assertTrue(javaString.isString())
        assertTrue(kotlinString.isString())
        assertFalse(1.isString())
    }

    @Test
    fun `isPrimitive() detects all primitive types`() {
        assertTrue(1.isPrimitive())
        assertTrue(true.isPrimitive())
        assertTrue('c'.isPrimitive())
        assertTrue(19.5.isPrimitive())

        assertFalse("Test".isPrimitive())
        assertFalse(SingletonFixture().isPrimitive())
    }

    private inline fun assertFailsWithInvalidSingletonException(lambda: () -> Unit) {
        assertFailsWith(InvalidSingletonException::class, lambda)
    }

}
