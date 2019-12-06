package com.adventofcode2019.day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SecureContainerTest {

    @Test
    fun isValidPassword() {
        assertTrue(isValidPassword("111111"))
        assertFalse(isValidPassword("223450"))
        assertFalse(isValidPassword("123789"))
    }

    @Test
    fun solvePuzzlePart1(){
        val text = this::class.java.classLoader.getResource("day4.txt").readText()
        val range = text.split('-').map { it.toInt() }

        val valid = mutableListOf<Int>()
        for (i in range[0]..range[1]) {
            if(isValidPassword(i.toString())){
                valid.add(i)
            }
        }

        val solution = valid.size

        assertEquals(925, solution)
    }

    @Test
    fun isValidPasswordWithExtraCriteria(){
        assertTrue(isValidPasswordWithExtraCriteria("112233"))
        assertFalse(isValidPasswordWithExtraCriteria("123444"))
        assertTrue(isValidPasswordWithExtraCriteria("111122"))
    }

    @Test
    fun solvePuzzlePart2(){
        val text = this::class.java.classLoader.getResource("day4.txt").readText()
        val range = text.split('-').map { it.toInt() }

        val valid = mutableListOf<Int>()
        for (i in range[0]..range[1]) {
            if(isValidPasswordWithExtraCriteria(i.toString())){
                valid.add(i)
            }
        }

        val solution = valid.size
        assertEquals(607, solution)
    }
}