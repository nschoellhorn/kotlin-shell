package eu.dreamcode.kotlinshell.test.fixtures.injectionresolver

class MultiConstructorClassFixture(
    val test: String,
    val test2: String
) {
    constructor(test: Int) : this("" + test, "something") {

    }
}
