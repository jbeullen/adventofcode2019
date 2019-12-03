package com.adventofcode2019.day2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class IntCodeKtTest {

    @Test
    fun intCodeMustStop() {
        assertArrayEquals(intArrayOf(99), intCode(intArrayOf(99)))
    }

    @Test
    fun intCodeMustAdd() {
        assertArrayEquals(intArrayOf(2,0,0,0,99), intCode(intArrayOf(1,0,0,0,99)))
        assertArrayEquals(intArrayOf(30,1,1,4,2,5,6,0,99), intCode(intArrayOf(1,1,1,4,99,5,6,0,99)))
    }
    @Test
    fun intCodeMustMultiply() {
        assertArrayEquals(intArrayOf(2,3,0,6,99), intCode(intArrayOf(2,3,0,3,99)))
        assertArrayEquals(intArrayOf(2,4,4,5,99,9801), intCode(intArrayOf(2,4,4,5,99,0)))
    }

    @Test
    fun solvePuzzlePart1(){
        val text = this::class.java.classLoader.getResource("day2.txt").readText()
        val split = text.split(',')
        var input = split.map { it.toInt() }.toIntArray()
        input[1] = 12
        input[2] = 2

        val code = intCode(input)

        //print(code[0])
        assertEquals(3790645, code[0])

    }

    @Test
    fun solvePuzzlePart2(){
        val puzzle = this::class.java.classLoader.getResource("day2.txt").readText()
            .split(',')
            .map { it.toInt() }
            .toIntArray()

        var solution = -1
        var i = 0
        var j = 0

        while (solution == -1){
            val input = puzzle.clone()
            input[1] = i
            input[2] = j

            val output = intCode(input)

            if (output[0] == 19690720){
               solution = 100 * i + j
            } else {
                i++
                if(i > 99){
                    j++
                    i = 0
                }
            }
        }

        //print(solution)
        assertEquals(6577, solution)

    }

}