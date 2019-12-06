package com.adventofcode2019.day3

import kotlin.math.absoluteValue

fun calculateManhattanDistance(wire1: String, wire2: String): Int {
    val operations1 = convertToOperationsList(wire1)
    val operations2 = convertToOperationsList(wire2)

    val coordinates1 = convertToCoordinates(operations1)
    val coordinates2 = convertToCoordinates(operations2)

    return coordinates1
        .intersect(coordinates2)
        .map { it.x.absoluteValue + it.y.absoluteValue }
        .min()!!
}

fun calculateMinimalDelay(wire1: String, wire2: String): Int {
    val operations1 = convertToOperationsList(wire1)
    val operations2 = convertToOperationsList(wire2)

    val coordinates1 = convertToCoordinates(operations1)
    val coordinates2 = convertToCoordinates(operations2)

    val intersections = coordinates1.intersect(coordinates2)

    return intersections.map {
        val steps1 = coordinates1.indexOf(it)+1
        val steps2 = coordinates2.indexOf(it)+1
        steps1 +steps2
    }.min()!!

}

private fun convertToCoordinates(operations: List<Operation>): List<Coordinate> {
    val coordinates = mutableListOf(Coordinate(0, 0))
    for (operation in operations) {
        val steps = operation.steps
        val last = coordinates.last()

        when (operation.direction) {
            'U' -> {
                for (i in 1..operation.steps) {
                    coordinates.add(last.copy(x = last.x + i))
                }
            }
            'D' -> {
                for (i in 1..operation.steps) {
                    coordinates.add(last.copy(x = last.x - i))
                }
            }
            'L' -> {
                for (i in 1..operation.steps) {
                    coordinates.add(last.copy(y = last.y - i))
                }
            }
            'R' -> {
                for (i in 1..operation.steps) {
                    coordinates.add(last.copy(y = last.y + i))
                }
            }
        }
    }
    coordinates.removeAt(0)
    return coordinates.toList()
}

private fun convertToOperationsList(wire: String) =
    wire.split(',').map { Operation(it[0], it.substring(1).toInt()) }

data class Coordinate(val x: Int, val y: Int)
data class Operation(val direction: Char, val steps: Int)