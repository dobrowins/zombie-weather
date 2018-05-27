package com.dobrowins.allsouls.repository.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Artem Dobrovinskiy
 */
@Entity(tableName = "weather_data")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "latitude")
    var latitude: Double? = null,

    @ColumnInfo(name = "longitude")
    var longitude: Double? = null,

    @ColumnInfo(name = "temperature")
    var temperature: Double? = null,

    @ColumnInfo(name = "isCelcius")
    var isCelcius: Boolean? = null,

    @ColumnInfo(name = "humidity")
    var humidity: Int? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "created_at")
    var timestamp: String = System.currentTimeMillis().toString()

)