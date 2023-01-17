// Automatically generated by the task generateIterators

@file:JvmMultifileClass
@file:JvmName("PrimitiveItersKt")

package com.kotakotik.kotalin.iterator

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/** An iterator over a sequence of values of type `UInt`. */
abstract class UIntIterator : Iterator<UInt> {
    final override fun next() = nextUInt()
    
    /** Returns the next value in the sequence without boxing. */
    abstract fun nextUInt(): UInt
}