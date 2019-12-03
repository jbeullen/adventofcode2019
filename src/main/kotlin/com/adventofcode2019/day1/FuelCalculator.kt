package com.adventofcode2019.day1

fun calculateFuelForModule(mass: Int): Int {
    return mass / 3 - 2
}

fun calculateFuelTakingMassOfFuelIntoAccount(mass: Int): Int {
    var totalFuel = 0
    var temp = mass
    while (temp > 0){
        val fuel = calculateFuelForModule(temp)
        if(fuel > 0) {
            totalFuel += fuel
        }
        temp = fuel
    }

    return totalFuel
}