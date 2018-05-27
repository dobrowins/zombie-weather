package com.dobrowins.allsouls.repository

import com.dobrowins.allsouls.domain.models.Coordinates
import com.dobrowins.allsouls.domain.models.Weather
import com.dobrowins.allsouls.domain.models.gson.ApiResponse
import com.dobrowins.allsouls.domain.repositories.DescriptionRepository
import com.dobrowins.allsouls.domain.repositories.KeyValueStorage
import com.dobrowins.allsouls.domain.repositories.WeatherRepository
import com.dobrowins.allsouls.repository.remote.ApiHolder
import com.dobrowins.allsouls.repository.remote.OpenWeatherApi
import com.dobrowins.allsouls.repository.room.dao.WeatherDataDao
import com.dobrowins.allsouls.repository.room.entities.WeatherData
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class WeatherRepositoryImpl
@Inject constructor(
    private val apiHolder: ApiHolder,
    private val weatherDataDao: WeatherDataDao,
    private val descriptionRepository: DescriptionRepository,
    private val keyValueStorage: KeyValueStorage
) : WeatherRepository {

    // todo error handling
    override fun requestWeather(coordinates: Coordinates): Single<ApiResponse> {
        val units = keyValueStorage.getStringValue(KEY_UNITS, "") as String
        val weatherCallBody = WeatherCallBody(coordinates, units)
        return makeGetWeatherCall(weatherCallBody)
    }

    override fun storeWeather(apiResponse: ApiResponse): Completable =
        toWeatherDataObject(apiResponse)
            .map { weatherDataDao.insertOrUpdate(it) }
            .ignoreElement()

    // todo: return dummy weather if can't fetch last one
    override fun getLastFetchedWeather(): Single<Weather> =
        weatherDataDao.findLast()
            .flatMap(::toWeatherObject)

    private fun makeGetWeatherCall(body: WeatherCallBody): Single<ApiResponse> {
        val api = apiHolder.openWeatherApi
        requireNotNull(api) { "ApiHolder has not been properly initialized" }
        val (latitiude, longitude) = body.coordinates
        val units = body.units
        return (api as OpenWeatherApi)
            .getWeather(
                latitiude.toString(),
                longitude.toString(),
                units,
                APP_ID
            )
    }

    private fun toWeatherDataObject(apiResponse: ApiResponse): Single<WeatherData> =
        Single.fromCallable {
            requireNotNull(apiResponse.coord) { "fetched coord object is null" }
            requireNotNull(apiResponse.coord?.lat) { "fetched latitude is null" }
            requireNotNull(apiResponse.coord?.lon) { "fetched longitude is null" }
            requireNotNull(apiResponse.main) { "fetched main object is null" }
            requireNotNull(apiResponse.main?.temp) { "fetched temperature is null" }
            requireNotNull(apiResponse.main?.humidity) { "fetched humdidity is null" }
            requireNotNull(apiResponse.id) { "fetched weather id is null" }
            val latitude = apiResponse.coord?.lat?.toDouble()
            val longitude = apiResponse.coord?.lon?.toDouble()
            val temperature = apiResponse.main?.temp as Double
            val humidity = apiResponse.main?.humidity as Int
            val weatherId = apiResponse.id as Int
            val description =
                descriptionRepository.getWeatherDescription(weatherId)
            val isCelcius =
                keyValueStorage.getStringValue(KEY_UNITS, "").equals(VALUE_METRIC)
            WeatherData(
                latitude = latitude,
                longitude = longitude,
                temperature = temperature,
                isCelcius = isCelcius,
                humidity = humidity,
                description = description
            )
        }

    private fun toWeatherObject(weatherData: WeatherData): Single<Weather> =
        Single.fromCallable {
            val latitude = weatherData.latitude as Double
            val longitude = weatherData.longitude as Double
            val temperature = weatherData.temperature as Double
            val isCelcius = weatherData.isCelcius as Boolean
            val humidity = weatherData.humidity as Int
            val description = weatherData.description as String
            Weather(
                latitude,
                longitude,
                temperature,
                isCelcius,
                humidity,
                description
            )
        }

    private data class WeatherCallBody(
        val coordinates: Coordinates,
        val units: String
    )

}
