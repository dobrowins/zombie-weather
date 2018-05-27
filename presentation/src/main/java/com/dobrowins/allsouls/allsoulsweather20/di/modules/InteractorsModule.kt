package com.dobrowins.allsouls.allsoulsweather20.di.modules

import com.dobrowins.allsouls.domain.SplashScreenInteractor
import com.dobrowins.allsouls.domain.WeatherInfoInteractor
import com.dobrowins.allsouls.domain.repositories.KeyValueStorage
import com.dobrowins.allsouls.domain.repositories.LocationRepository
import com.dobrowins.allsouls.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
@Module
class InteractorsModule {

    @Provides
    @Singleton
    fun splashScreenInteractor(
        weatherRepository: WeatherRepository,
        locationRepository: LocationRepository,
        keyValueStorage: KeyValueStorage
    ): SplashScreenInteractor =
        SplashScreenInteractor(
            weatherRepository,
            locationRepository,
            keyValueStorage
        )

    @Provides
    @Singleton
    fun weatherInfoInteractor(
        weatherRepository: WeatherRepository,
        keyValueStorage: KeyValueStorage
    ): WeatherInfoInteractor =
        WeatherInfoInteractor(weatherRepository, keyValueStorage)
}