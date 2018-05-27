package com.dobrowins.allsouls.repository.remote

import com.dobrowins.allsouls.domain.models.gson.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Artem Dobrovinskiy
 */
interface OpenWeatherApi {

    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ) : Single<ApiResponse>

}