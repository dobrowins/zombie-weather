package com.dobrowins.allsouls.domain.models

/**
 * @author Artem Dobrovinskiy
 */
data class Weather(
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val isCelcius: Boolean,
    val humidity: Int,
    val description: String
)