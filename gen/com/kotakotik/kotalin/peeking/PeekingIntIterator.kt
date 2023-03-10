// Automatically generated by the task generatePeekingIters

@file:JvmMultifileClass
@file:JvmName("PeekingIteratorKt")

package com.kotakotik.kotalin.peeking


import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * A [PeekingIterator] that iterates over [Int]s without boxing
 * @see PeekingIterator, IntIterator
*/
class PeekingIntIterator(private val innerIter: IntIterator) : IntIterator(), PeekingIterator<Int> {
    private var peeked: Int = 0
    private var hasPeeked = false
    
    /**
     * Peeks the next element without boxing
     * @see peek
    */
    fun peekInt(): Int {
        if(!hasPeeked) {
            hasPeeked = true
            peeked = innerIter.nextInt()
        }
        return peeked
    }
    
    override fun peek() = peekInt()
    
    override fun nextInt() = if(hasPeeked) {
        hasPeeked = false
        peeked
    } else {
        innerIter.nextInt()
    }
    
    override fun hasNext() =
        hasPeeked || innerIter.hasNext()
}

/**
 * Creates a new [PeekingIntIterator] wrapping around this iterator
 *
 * @see PeekingIterator, PeekingIntIterator
 */
fun IntIterator.peeking() =
    PeekingIntIterator(this)