package com.kotakotik.kotalin

import com.kotakotik.kotalin.peeking.peeking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class PeekTest {
    @Test
    fun test() {
        val iter = listOf("a", "b", "c").iterator().peeking()
        assertEquals("a", iter.peek())
        assertEquals("a", iter.next())
        assertEquals("b", iter.peek())
        assertEquals("b", iter.next())
        assertEquals("c", iter.next())
        assertFalse(iter.hasNext())
    }

    @Test
    fun charTest() {
        val iter = "abc".iterator().peeking()
        assertEquals('a', iter.peekChar())
        assertEquals('a', iter.nextChar())
        assertEquals('b', iter.peekChar())
        assertEquals('b', iter.nextChar())
        assertEquals('c', iter.nextChar())
        assertFalse(iter.hasNext())
    }
}