package com.adventofcode2019.day4
val regex = """(11|22|33|44|55|66|77|88|99)""".toRegex()

fun isValidPassword(password: String): Boolean {
    if (password.length != 6){
        return false
    }

    if(!regex.containsMatchIn(password)){
        return false
    }

    val chars = password.toCharArray()
    for (i in 1 until chars.size) {
        val first = chars[i-1]
        val second = chars[i]
        if(first > second) return false
    }

    return true
}

fun isValidPasswordWithExtraCriteria(password: String): Boolean{
    if(!isValidPassword(password)) {
        return false
    }
    val chars = password.toCharArray()
    for (i in 1 until chars.size) {
        val first = chars[i-1]
        val second = chars[i]
        var previous = 'X'
        var next = 'X'
        if(i != 1){
            previous = chars[i-2]
        }
        if(i != chars.size - 1){
            next = chars[i+1]
        }
        if(previous != first && first == second && second != next){
            return true
        }
    }
    return false

}