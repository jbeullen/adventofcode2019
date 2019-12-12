package com.adventofcode2019.day5.memory

import com.adventofcode2019.day5.Memory

class IntArrayMemory constructor(private val array: IntArray) : Memory {

    override fun getValue(address: Int) : Int {
        return array[address]
    }

    override fun setValue(address: Int, value: Int) {
        array[address] = value
    }
}