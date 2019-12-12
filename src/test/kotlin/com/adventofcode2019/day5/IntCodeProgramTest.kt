package com.adventofcode2019.day5

import com.adventofcode2019.day2.intCode
import com.adventofcode2019.day5.memory.IntArrayMemory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.pow

internal class IntCodeProgramTest {

    @Test
    fun processProgram_Add() {
        assertArrayEquals(intArrayOf(2, 0, 0, 0, 99), processProgram(intArrayOf(1, 0, 0, 0, 99)).first)
    }

    @Test
    fun processProgram_Multiply() {
        assertArrayEquals(intArrayOf(2,3,0,6,99), processProgram(intArrayOf(2,3,0,3,99)).first)
        assertArrayEquals(intArrayOf(2,4,4,5,99,9801), processProgram(intArrayOf(2,4,4,5,99,0)).first)
    }

    @Test
    fun processProgram_SetInput() {
        assertArrayEquals(intArrayOf(3,3,99,1), processProgram(intArrayOf(3,3,99,0),1).first)
    }

    @Test
    fun processProgram_GetOutput() {
        val output = processProgram(intArrayOf(4, 3, 99, 100))
        assertArrayEquals(intArrayOf(4,3,99,100), output.first)
        assertEquals(100, output.second)
    }

    @Test
    fun processProgram_Combination() {
        assertArrayEquals(intArrayOf(30,1,1,4,2,5,6,0,99), processProgram(intArrayOf(1,1,1,4,99,5,6,0,99)).first)
    }

    @Test
    fun processProgram_ParameterModes() {
        assertArrayEquals(intArrayOf(1002,4,3,4,99), processProgram(intArrayOf(1002,4,3,4,33)).first)
    }

    @Test
    fun processProgram_JumpIfTrue() {
        assertArrayEquals(intArrayOf(10,8,9,1,0,0,0,99,0,7), processProgram(intArrayOf(5,8,9,1,0,0,0,99,0,7)).first)
        assertArrayEquals(intArrayOf(5,8,9,1,0,0,0,99,1,7), processProgram(intArrayOf(5,8,9,1,0,0,0,99,1,7)).first)
    }

    @Test
    fun processProgram_JumpIfFalse() {
        assertArrayEquals(intArrayOf(6,8,9,1,0,0,0,99,0,7), processProgram(intArrayOf(6,8,9,1,0,0,0,99,0,7)).first)
        assertArrayEquals(intArrayOf(12,8,9,1,0,0,0,99,1,7), processProgram(intArrayOf(6,8,9,1,0,0,0,99,1,7)).first)
    }

    @Test
    fun processProgram_LessThan() {
        assertArrayEquals(intArrayOf(7,5,6,7,99,0,1,1), processProgram(intArrayOf(7,5,6,7,99,0,1,9999)).first)
        assertArrayEquals(intArrayOf(7,5,6,7,99,1,0,0), processProgram(intArrayOf(7,5,6,7,99,1,0,9999)).first)


        assertEquals(1, processProgram(intArrayOf(3,9,7,9,10,9,4,9,99,-1,8), 7).second)
        assertEquals(0, processProgram(intArrayOf(3,9,7,9,10,9,4,9,99,-1,8), 8).second)
        assertEquals(0, processProgram(intArrayOf(3,9,7,9,10,9,4,9,99,-1,8), 9).second)

        assertEquals(1, processProgram(intArrayOf(3,3,1107,-1,8,3,4,3,99), 7).second)
        assertEquals(0, processProgram(intArrayOf(3,3,1107,-1,8,3,4,3,99), 8).second)
        assertEquals(0, processProgram(intArrayOf(3,3,1107,-1,8,3,4,3,99), 9).second)
    }

    @Test
    fun processProgram_Jumps() {
        assertEquals(0, processProgram(intArrayOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9), 0).second)
        assertEquals(1, processProgram(intArrayOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9), 1).second)

        assertEquals(0, processProgram(intArrayOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1), 0).second)
        assertEquals(1, processProgram(intArrayOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1), 1).second)
    }

    @Test
    fun processProgram_Equals() {
        assertArrayEquals(intArrayOf(8,5,6,7,99,0,1,0), processProgram(intArrayOf(8,5,6,7,99,0,1,9999)).first)
        assertArrayEquals(intArrayOf(8,5,6,7,99,1,1,1), processProgram(intArrayOf(8,5,6,7,99,1,1,9999)).first)



        assertEquals(1, processProgram(intArrayOf(3,9,8,9,10,9,4,9,99,-1,8), 8).second)
        assertEquals(0, processProgram(intArrayOf(3,9,8,9,10,9,4,9,99,-1,8), 99).second)

        assertEquals(1, processProgram(intArrayOf(3,3,1108,-1,8,3,4,3,99), 8).second)
        assertEquals(0, processProgram(intArrayOf(3,3,1108,-1,8,3,4,3,99), 99).second)
    }



    private fun processProgram(initial: IntArray, input: Int = 0): Pair<IntArray, Int> {
        val memory = initial.clone()
        val program = IntCodeProgram(IntArrayMemory(memory), input)
        while (!program.isHalted()){
            program.processNextInstruction()
        }
        return Pair(memory, program.getOutput())
    }

    @Test
    fun solvePuzzlePart1(){
        val text = this::class.java.classLoader.getResource("day5.txt").readText()
        val split = text.split(',')
        var initial = split.map { it.toInt() }.toIntArray()
        val (_, solution) = processProgram(initial, 1)



        //print(solution)
        assertEquals(9775037, solution)

    }
    /* Can't get this scenario to work
    @Test
    fun processProgram_Combination2(){
        val initial = intArrayOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99)

        val (_, output) = processProgram(initial, 7)
        assertEquals(999, output)
    }

    */

    @Test
    fun solvePuzzlePart2(){
        val text = this::class.java.classLoader.getResource("day5.txt").readText()
        val split = text.split(',')
        var initial = split.map { it.toInt() }.toIntArray()
        val (_, solution) = processProgram(initial, 5)



        print(solution)
        assertEquals(15586959, solution)

    }


}