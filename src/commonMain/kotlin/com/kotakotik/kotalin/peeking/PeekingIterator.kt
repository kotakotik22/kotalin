@file:JvmMultifileClass()
@file:JvmName("PeekingIteratorKt")

package com.kotakotik.kotalin.peeking

import kotlin.js.JsName
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.jvm.JvmSynthetic

/**
 * An iterator with the ability to peek without consuming the next element
 *
 * [peek] can be used to get the latest element in the inner iterator without consuming it
 *
 * **Note: Implementations of this interface always consume the next element in the inner iterator, so side effects of calling [next] on the inner iterator may apply when calling peek**
 */
interface PeekingIterator<T> : Iterator<T> {
    /**
     * Returns the next element of the iterator without actually consuming it, so calling [peek] will always return whatever [next] would return next
     *
     * **Note: This still consumes the element in the inner iterator, so side effects of [next] on the inner iterator may apply**
     */
    fun peek(): T
    // todo: should we override the docs of the iterator methods?
}

/**
 * Creates a new peeking iterator wrapping around this iterator
 *
 * @see PeekingIterator
 */
fun <T> Iterator<T>.peeking() =
    PeekingIterator(this)

/**
 * Creates a new [PeekingIterator] that iterators over objects
 *
 * For performance, primitive versions of `PeekingIterator` may be used to prevent boxing
 */
@JsName("objPeekingIter")
@JvmSynthetic // Iterator<T>.peeking() is an alias for this method
fun <T> PeekingIterator(innerIter: Iterator<T>) =
    object : PeekingIterator<T> {
        var hasPeeked = false
        var peeked: T? = null

        override fun peek(): T {
            if (!hasPeeked) {
                peeked = next()
                hasPeeked = true
            }
            @Suppress("UNCHECKED_CAST")
            return peeked as T
        }

        override fun hasNext(): Boolean =
            hasPeeked || innerIter.hasNext()

        override fun next(): T =
            if (hasPeeked) {
                hasPeeked = false
                val p = peeked
                peeked = null
                @Suppress("UNCHECKED_CAST")
                p as T
            } else {
                innerIter.next()
            }
    }