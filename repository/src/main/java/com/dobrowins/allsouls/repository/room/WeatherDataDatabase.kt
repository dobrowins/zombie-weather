package com.dobrowins.allsouls.repository.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dobrowins.allsouls.repository.room.dao.WeatherDataDao
import com.dobrowins.allsouls.repository.room.entities.WeatherData

/**
 * @author Artem Dobrovinskiy
 */
@Database(version = 0, entities = arrayOf(WeatherData::class))
abstract class WeatherDataDatabase: RoomDatabase() {

    abstract fun weatherDataDao(): WeatherDataDao

}