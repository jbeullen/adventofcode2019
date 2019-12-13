package com.adventofcode2019.day6

import kotlin.math.absoluteValue


class OrbitMap {
    private val planets = hashMapOf<String, Pair<String, String?>>()

    fun getPlanets(): List<String>{
        return planets.keys.toList()
    }

    fun addPlanet(planet: String, directOrbit: String? = null) {
        planets[planet] = Pair(planet, directOrbit)
        directOrbit?.let { planets.putIfAbsent(it, Pair(directOrbit, null)) }

    }

    fun calculateOrbits(planet: String): Int {
        return getPlanetsToCenter(planet).count()

    }

    fun calculateDistance(planet1: String, planet2: String) : Int{
        val planetsToCenter1 = getPlanetsToCenter(planet1)
        val planetsToCenter2 = getPlanetsToCenter(planet2)
        val intersect = planetsToCenter1.intersect(planetsToCenter2)


        val listTilShared1 = planetsToCenter1 - intersect
        val listTilShared2 = planetsToCenter2 - intersect


        val i = listTilShared1.count() + listTilShared2.count()
        return i - 2
    }

    private fun getPlanetsToCenter(planet: String): List<String> {
        var planetsToCenter = mutableListOf<String>()
        var key = planet
        while (planets[key]?.second != null) {
            planetsToCenter.add(planets[key]?.first!!)
            key = planets[key]?.second!!
        }
        return planetsToCenter.toList()
    }
}