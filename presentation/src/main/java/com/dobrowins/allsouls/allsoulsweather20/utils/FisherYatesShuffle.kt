package com.dobrowins.allsouls.allsoulsweather20.utils

import java.util.*

/**
 * @author Artem Dobrovinskiy
 */
object FisherYatesShuffle {

    fun getRandomInt(power: Int): Int {
        var a = 0
        val result: Int
        val intArray = IntArray(power, { i -> i })
        val random = Random()
        for (i in intArray.size - 1 downTo 1) {
            // Fisher-Yates shuffle
            val index = random.nextInt(i + 1)
            a = intArray[index]
            intArray[index] = intArray[i]
            intArray[i] = a
        }
        result = a
        return result
    }

}