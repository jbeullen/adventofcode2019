package com.adventofcode2019.day6

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OrbitMapTest {

    @Test
    fun newOrbitMap_contains_no_planets() {
        val orbitMap = OrbitMap()
        assertEquals(0, orbitMap.getPlanets().size)
    }

    @Test
    fun addPlanet_given_planetHasNoDirectOrbit_then_orbitMapContainsOnlyPlanet() {
        val orbitMap = OrbitMap()

        orbitMap.addPlanet("COM")

        assertEquals(1, orbitMap.getPlanets().size)
        assertEquals(true, orbitMap.getPlanets().contains("COM"))
    }

    @Test
    fun addPlanet_given_planetHasDirectOrbit_then_orbitMapContainsPlanetAndDirectOrbit() {
        val orbitMap = OrbitMap()

        orbitMap.addPlanet("B", "COM")

        assertEquals(2, orbitMap.getPlanets().size)
        assertEquals(true, orbitMap.getPlanets().contains("COM"))
        assertEquals(true, orbitMap.getPlanets().contains("B"))
    }

    @Test
    fun addPlanet_given_planetHasDirectOrbitAndOrbitMapContainsDirectOrbit_then_orbitMapContainsPlanetAndDirectOrbit() {
        val orbitMap = OrbitMap()
        orbitMap.addPlanet("COM")


        orbitMap.addPlanet("B", "COM")

        assertEquals(2, orbitMap.getPlanets().size)
        assertEquals(true, orbitMap.getPlanets().contains("COM"))
        assertEquals(true, orbitMap.getPlanets().contains("B"))
    }

    @Test
    fun calculateOrbits() {
        val orbitMap = OrbitMap()
        orbitMap.addPlanet("COM")
        assertEquals(0, orbitMap.calculateOrbits("COM"))

        orbitMap.addPlanet("B", "COM")
        assertEquals(1, orbitMap.calculateOrbits("B"))

        orbitMap.addPlanet("C", "B")
        assertEquals(2, orbitMap.calculateOrbits("C"))
    }

    @Test
    fun calculateOrbits_blackbox() {
        val input = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L"
        val orbitMap = OrbitMap()
        input
            .split('\n')
            .forEach {
                val split = it.split(')')
                orbitMap.addPlanet(split[1], split[0])
            }

        val totalOrbits = orbitMap
            .getPlanets()
            .map {
                orbitMap.calculateOrbits(it)
            }.sum()

        assertEquals(42, totalOrbits)
    }

    @Test
    fun calculateDistance_blackbox() {
        val input = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L\n" +
                "K)YOU\n" +
                "I)SAN"
        val orbitMap = OrbitMap()
        input
            .split('\n')
            .forEach {
                val split = it.split(')')
                orbitMap.addPlanet(split[1], split[0])
            }

        val distance = orbitMap.calculateDistance("SAN", "YOU")


        assertEquals(4, distance)
    }


    @Test
    fun solvePuzzlePart1(){
        val orbitMap = OrbitMap()

        val text = this::class.java.classLoader.getResource("day6.txt").readText()
        text
            .split('\n')
            .forEach {
                val split = it.split(')')
                orbitMap.addPlanet(split[1], split[0])
            }

        val totalOrbits = orbitMap
            .getPlanets()
            .map {
                orbitMap.calculateOrbits(it)
            }.sum()

        assertEquals(147807, totalOrbits)

    }

    @Test
    fun solvePuzzlePart2(){
        val orbitMap = OrbitMap()

        val text = this::class.java.classLoader.getResource("day6.txt").readText()
        text
            .split('\n')
            .forEach {
                val split = it.split(')')
                orbitMap.addPlanet(split[1], split[0])
            }

        val distance = orbitMap.calculateDistance("YOU", "SAN")

        assertEquals(229, distance)

    }

}