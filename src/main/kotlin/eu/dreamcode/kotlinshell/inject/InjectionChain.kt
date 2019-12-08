package eu.dreamcode.kotlinshell.inject

import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

typealias Constructor = KFunction<*>

fun buildDependencyGraph(entryPoint: KClass<*>): DefaultDirectedGraph<KClass<*>, DefaultEdge> {
    return DefaultDirectedGraph<KClass<*>, DefaultEdge>(DefaultEdge::class.java)
}

/*

Directed Graph:
    - Node = KClass
    - Edge = Parameter of resolvable constructor


                ClassWithSimpleDependency
                 /
               /
            /
           V
     SomeDependency

 */
