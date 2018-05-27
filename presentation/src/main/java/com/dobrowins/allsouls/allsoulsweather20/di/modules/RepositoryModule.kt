package com.dobrowins.allsouls.allsoulsweather20.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.dobrowins.allsouls.allsoulsweather20.BuildConfig
import com.dobrowins.allsouls.allsoulsweather20.utils.LocationRepositoryImpl
import com.dobrowins.allsouls.domain.repositories.DescriptionRepository
import com.dobrowins.allsouls.domain.repositories.KeyValueStorage
import com.dobrowins.allsouls.domain.repositories.LocationRepository
import com.dobrowins.allsouls.domain.repositories.WeatherRepository
import com.dobrowins.allsouls.repository.DescriptionRepositoryImpl
import com.dobrowins.allsouls.repository.KeyValueStorageImpl
import com.dobrowins.allsouls.repository.WeatherRepositoryImpl
import com.dobrowins.allsouls.repository.remote.ApiHolder
import com.dobrowins.allsouls.repository.remote.OpenWeatherApi
import com.dobrowins.allsouls.repository.room.WeatherDataDatabase
import com.dobrowins.allsouls.repository.room.dao.WeatherDataDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun weatherDataDao(context: Context): WeatherDataDao =
        Room.databaseBuilder(
            context,
            WeatherDataDatabase::class.java,
            "weather_data_db"
        )
            .build()
            .weatherDataDao()

    @Provides
    @Singleton
    fun weatherRepository(
        apiHolder: ApiHolder,
        weatherDataDao: WeatherDataDao,
        descriptionRepository: DescriptionRepository,
        keyValueStorage: KeyValueStorage
    ): WeatherRepository =
        WeatherRepositoryImpl(
            apiHolder,
            weatherDataDao,
            descriptionRepository,
            keyValueStorage
        )

    @Provides
    @Singleton
    fun locationRepository(
        context: Context,
        weatherDataDao: WeatherDataDao
    ): LocationRepository =
        LocationRepositoryImpl(context, weatherDataDao)

    @Provides
    @Singleton
    fun descriptionRepository(resources: Resources): DescriptionRepository =
        DescriptionRepositoryImpl(resources)

    @Provides
    @Singleton
    fun keyValueStorage(sharedPreferences: SharedPreferences): KeyValueStorage =
        KeyValueStorageImpl(sharedPreferences)

    @Provides
    @Singleton
    fun apiHolder(): ApiHolder = ApiHolder()

    @Provides
    @Singleton
    fun api(okHttpClient: OkHttpClient, apiHolder: ApiHolder): OpenWeatherApi {
        val api = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
        apiHolder.openWeatherApi = api
        return api
    }

    @Provides
    @Singleton
    fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return when (BuildConfig.DEBUG) {
            true ->
                client
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
            false -> client.build()
        }
    }

}