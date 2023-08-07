package es.sfernandez.libraries.library4k

import java.util.function.Function

fun <T> sequenceWith(numElements: Int, generator : Function<Int, T>) : Sequence<T> {
    return generateSequence(0) {it + 1}
        .take(numElements)
        .map(generator::apply)
}

fun <T> listWith(numElements: Int, generator : Function<Int, T>) : List<T> {
    return sequenceWith(numElements, generator).toList()
}

fun <T> mutableListWith(numElements: Int, generator : Function<Int, T>) : MutableList<T> {
    return sequenceWith(numElements, generator).toMutableList()
}