package com.dobrowins.allsouls.domain.repositories

import com.dobrowins.allsouls.domain.models.Coordinates
import com.dobrowins.allsouls.domain.models.Weather
import com.dobrowins.allsouls.domain.models.gson.ApiResponse
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * @author Artem Dobrovinskiy
 */
interface WeatherRepository {
    fun requestWeather(coordinates: Coordinates): Single<ApiResponse>
    fun storeWeather(apiResponse: ApiResponse): Completable
    fun getLastFetchedWeather(): Single<Weather>
}