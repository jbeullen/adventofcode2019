package com.adventofcode2019.day5

import kotlin.math.pow

class IntCodeProgram constructor(private val memory: Memory, private val input: Int = 0) {
    private var output: Int = 0
    private var instructionPointer = 0

    fun isHalted() : Boolean {
        return getOpcode() == 99
    }

    fun processNextInstruction(){
        if(!isHalted()){
            when(getOpcode()){
                1 -> {
                    val value1 = getParameterValue(1)
                    val value2 = getParameterValue(2)
                    val address = memory.getValue(instructionPointer+3)
                    memory.setValue(address, value1+value2)
                    instructionPointer += 4
                }
                2 -> {
                    val value1 = getParameterValue(1)
                    val value2 = getParameterValue(2)
                    val address = memory.getValue(instructionPointer+3)
                    memory.setValue(address, value1*value2)
                    instructionPointer += 4
                }
                3 -> {
                    val address = memory.getValue(instructionPointer+1)
                    memory.setValue(address, input)
                    instructionPointer += 2
                }
                4 -> {
                    val address = memory.getValue(instructionPointer+1)
                    output = memory.getValue(address)

                    instructionPointer += 2
                }
                5 -> {
                    val value1 = getParameterValue(1)
                    if(value1 != 0){
                        instructionPointer = getParameterValue(2)
                    } else {
                        instructionPointer += 3
                    }
                }
                6 -> {
                    val value1 = getParameterValue(1)
                    if(value1 == 0){
                        instructionPointer = getParameterValue(2)
                    } else {
                        instructionPointer += 3
                    }
                }
                7 -> {
                    var result = 0
                    val value1 = getParameterValue(1)
                    val value2 = getParameterValue(2)
                    if(value1 < value2){
                        result = 1
                    }
                    val address = memory.getValue(instructionPointer+3)
                    memory.setValue(address, result)
                    instructionPointer += 4

                }
                8 -> {
                    var result = 0
                    val value1 = getParameterValue(1)
                    val value2 = getParameterValue(2)
                    if(value1 == value2){
                        result = 1
                    }
                    val address = memory.getValue(instructionPointer+3)
                    memory.setValue(address, result)

                    instructionPointer += 4
                }
            }
        }
    }

    fun getOutput(): Int {
        return output
    }

    private fun getOpcode(): Int {
        return memory.getValue(instructionPointer) % 100
    }

    private fun getParameterValue(index: Int) : Int {
        val parameterModes = memory.getValue(instructionPointer) / 100

        var parameterMode = 0

        when(index){
            1 -> {
                parameterMode = parameterModes % 10
            }
            2 -> {
                parameterMode = (parameterModes / 10) % 10
            }
            3 -> {
                parameterMode = (parameterModes / 100) % 10
            }
        }

        if(parameterMode == 1){
            return memory.getValue(instructionPointer+index)
        } else {
            val address = memory.getValue(instructionPointer + index)
            return memory.getValue(address)
        }
    }
}