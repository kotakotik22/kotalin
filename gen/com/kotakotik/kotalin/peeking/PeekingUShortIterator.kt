// Automatically generated by the task generatePeekingIters

@file:JvmMultifileClass
@file:JvmName("PeekingIteratorKt")

package com.kotakotik.kotalin.peeking

import com.kotakotik.kotalin.iterator.UShortIterator
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * A [PeekingIterator] that iterates over [UShort]s without boxing
 * @see PeekingIterator, UShortIterator
*/
class PeekingUShortIterator(private val innerIter: UShortIterator) : UShortIterator(), PeekingIterator<UShort> {
    private var peeked: UShort = 0u
    private var hasPeeked = false
    
    /**
     * Peeks the next element without boxing
     * @see peek
    */
    fun peekUShort(): UShort {
        if(!hasPeeked) {
            hasPeeked = true
            peeked = innerIter.nextUShort()
        }
        return peeked
    }
    
    override fun peek() = peekUShort()
    
    override fun nextUShort() = if(hasPeeked) {
        hasPeeked = false
        peeked
    } else {
        innerIter.nextUShort()
    }
    
    override fun hasNext() =
        hasPeeked || innerIter.hasNext()
}

/**
 * Creates a new [PeekingUShortIterator] wrapping around this iterator
 *
 * @see PeekingIterator, PeekingUShortIterator
 */
fun UShortIterator.peeking() =
    PeekingUShortIterator(this)