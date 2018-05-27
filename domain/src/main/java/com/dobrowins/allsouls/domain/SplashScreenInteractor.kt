package com.dobrowins.allsouls.domain

import KEY_UNITS
import VALUE_METRIC
import com.dobrowins.allsouls.domain.repositories.KeyValueStorage
import com.dobrowins.allsouls.domain.repositories.LocationRepository
import com.dobrowins.allsouls.domain.repositories.WeatherRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class SplashScreenInteractor
@Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
    private val keyValueStorage: KeyValueStorage
) {

    fun storeDefaultUnits(): Completable =
        Completable.fromAction {
            val isMetricsSaved = keyValueStorage.getStringValue(KEY_UNITS, null) != null
            if (!isMetricsSaved) {
                keyValueStorage.putStringValue(KEY_UNITS, VALUE_METRIC)
            }
        }

    fun isNetworkAccessible(): Single<Boolean> =
        Single.fromCallable {
            val timeout = 1500
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            socket.connect(socketAddress, timeout)
            socket.close()
            true
        }
            .onErrorReturnItem(false)

    fun requestAndStoreWeather(): Completable =
        locationRepository.getLastKnownLocation()
            .flatMap(locationRepository::storeLocation)
            .flatMap(weatherRepository::requestWeather)
            .flatMapCompletable(weatherRepository::storeWeather)

}