package com.adventofcode2019.day5

import com.adventofcode2019.day5.memory.IntArrayMemory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MemoryTest {

    @Test
    fun should_return_value_at_address(){
        val memory = IntArrayMemory(intArrayOf(1))

        assertEquals(1, memory.getValue(0))
    }

    @Test
    fun should_set_value_at_address(){
        val memory = IntArrayMemory(intArrayOf(0))
        assertEquals(0, memory.getValue(0))

        memory.setValue(0, 1)

        assertEquals(1, memory.getValue(0))
    }
}