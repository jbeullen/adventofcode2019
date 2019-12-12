package com.adventofcode2019.day5

interface Memory {
    fun getValue(address: Int) : Int
    fun setValue(address: Int, value: Int)
}