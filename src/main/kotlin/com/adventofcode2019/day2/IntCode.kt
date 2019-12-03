package com.adventofcode2019.day2

fun intCode(input: IntArray): IntArray {
    var index = 0
    var output =  input.clone()
    while (output[index] != 99){
        when(output[index]){
            1 -> {
                output[output[index+3]] = output[output[index+1]] + output[output[index+2]]
                index += 4
            }
            2 -> {
                output[output[index+3]] = output[output[index+1]] * output[output[index+2]]
                index += 4
            }

        }
    }
    return output
}