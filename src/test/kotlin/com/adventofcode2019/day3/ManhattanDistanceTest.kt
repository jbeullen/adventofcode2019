package com.adventofcode2019.day3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ManhattanDistanceTest {

    @Test
    fun calculateManhattanDistance() {
        assertEquals(6, calculateManhattanDistance("R8,U5,L5,D3", "U7,R6,D4,L4"))
        assertEquals(159, calculateManhattanDistance("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"))
        assertEquals(135, calculateManhattanDistance("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"))
    }

    @Test
    fun solvePuzzlePart1(){
        val text = this::class.java.classLoader.getResource("day3.txt").readText()
        val wires = text.split('\n')

        val solution = calculateManhattanDistance(wires[0], wires[1])

        //println(solution)
        assertEquals(1264, solution)
    }

    @Test
    fun calculateMinimalDelay() {
        assertEquals(30, calculateMinimalDelay("R8,U5,L5,D3", "U7,R6,D4,L4"))
        assertEquals(610, calculateMinimalDelay("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"))
        assertEquals(410, calculateMinimalDelay("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"))
    }

    @Test
    fun solvePuzzlePart2(){
        val text = this::class.java.classLoader.getResource("day3.txt").readText()
        val wires = text.split('\n')

        val solution = calculateMinimalDelay(wires[0], wires[1])

        println(solution)
        assertEquals(37390, solution)
    }
}