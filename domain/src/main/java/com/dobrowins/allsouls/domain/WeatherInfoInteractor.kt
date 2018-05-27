package com.dobrowins.allsouls.domain

import KEY_UNITS
import VALUE_IMPERIAL
import VALUE_METRIC
import com.dobrowins.allsouls.domain.models.Weather
import com.dobrowins.allsouls.domain.repositories.KeyValueStorage
import com.dobrowins.allsouls.domain.repositories.WeatherRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class WeatherInfoInteractor
@Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val keyValueStorage: KeyValueStorage
) {

    fun isUnitsMetric(): Single<Boolean> =
        Single.fromCallable {
            val savedUnits = keyValueStorage.getStringValue(KEY_UNITS, "")
            savedUnits.equals(VALUE_METRIC)
        }

    fun getLastFetchedWeather(): Single<Weather> =
        weatherRepository.getLastFetchedWeather()

    fun applyMetricsChanged(): Completable =
        Completable.fromAction {
            val isMetric =
                keyValueStorage.getStringValue(KEY_UNITS, "").equals(VALUE_METRIC)
            keyValueStorage.putStringValue(KEY_UNITS,
                if (isMetric) VALUE_IMPERIAL else VALUE_METRIC
            )
        }

}
