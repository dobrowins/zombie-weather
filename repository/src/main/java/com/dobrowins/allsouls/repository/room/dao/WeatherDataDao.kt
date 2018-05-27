package com.dobrowins.allsouls.repository.room.dao

import android.arch.persistence.room.*
import com.dobrowins.allsouls.repository.room.entities.WeatherData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Artem Dobrovinskiy
 */
@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
@Dao
interface WeatherDataDao {

    @Query("SELECT * FROM weather_data")
    fun getAll(): Flowable<List<WeatherData>>

    @Query("SELECT * FROM weather_data ORDER BY created_at DESC LIMIT 1")
    @Throws(EmptyResultSetException::class)
    fun findLast(): Single<WeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(weatherData: WeatherData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(list: List<WeatherData>)

    @Delete
    fun delete(list: List<WeatherData>)

    @Query("DELETE FROM weather_data")
    fun deleteAll()

}