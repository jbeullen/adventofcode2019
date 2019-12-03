package com.adventofcode2019.day2

fun intCode(input: IntArray): IntArray {
    var index = 0
    while (input[index] != 99){
        when(input[index]){
            1 -> {
                input[input[index+3]] = input[input[index+1]] + input[input[index+2]]
                index += 4
            }
            2 -> {
                input[input[index+3]] = input[input[index+1]] * input[input[index+2]]
                index += 4
            }

        }
    }
    return input
}