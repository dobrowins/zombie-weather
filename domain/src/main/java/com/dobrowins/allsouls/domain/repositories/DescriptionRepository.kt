package com.dobrowins.allsouls.domain.repositories

import io.reactivex.Single

/**
 * @author Artem Dobrovinskiy
 */
interface DescriptionRepository {
    fun getWeatherDescription(weatherId: Int): String
}