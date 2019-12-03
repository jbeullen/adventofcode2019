package com.adventofcode2019.day1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.net.URL

internal class FuelCalculatorKtTest {

    @Test
    fun calculateFuelForModule() {
        assertEquals(
            2,
            calculateFuelForModule(12)
        )
        assertEquals(
            2,
            calculateFuelForModule(14)
        )
        assertEquals(
            654,
            calculateFuelForModule(1969)
        )
        assertEquals(
            33583,
            calculateFuelForModule(100756)
        )
    }

    @Test
    fun calculateFuelForJourney(){
        var total = 0
        val text = this::class.java.classLoader.getResource("day1.txt").readText()
        val lines = text.split('\n')
        for (line in lines){
            val mass = line.trim().toInt()
            val fuel = calculateFuelForModule(mass)
            total += fuel
        }
        //println(total)

        assertEquals(3423279, total)
    }
}