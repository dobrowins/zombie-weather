package com.dobrowins.allsouls.allsoulsweather20.utils

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.dobrowins.allsouls.domain.repositories.LocationRepository
import com.dobrowins.allsouls.domain.models.Coordinates
import com.dobrowins.allsouls.repository.room.dao.WeatherDataDao
import com.dobrowins.allsouls.repository.room.entities.WeatherData
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class LocationRepositoryImpl
@Inject constructor(
    private val context: Context,
    private val weatherDataDao: WeatherDataDao
) : LocationRepository {

    companion object {
        private const val NETWORK = LocationManager.NETWORK_PROVIDER
        private const val GPS = LocationManager.GPS_PROVIDER
    }

    private val locationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Throws(SecurityException::class, IllegalArgumentException::class)
    override fun getLastKnownLocation(): Single<Coordinates> =
        Single.fromCallable {
            val location = locationManager.getLastKnownLocation(NETWORK)
            requireNotNull(location) { "location provider is not enabled" }
            val latitude = location.latitude
            val longitude = location.longitude
            Coordinates(latitude, longitude)
        }

    override fun storeLocation(coordinates: Coordinates): Single<Coordinates> =
        Single.fromCallable {
            val (latitude, longitude) = coordinates
            val data = WeatherData(latitude = latitude, longitude = longitude)
            weatherDataDao.insertOrUpdate(data)
            coordinates
        }

    @Throws(SecurityException::class)
    override fun getLocationUpdates() {
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                // use location update
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        }
        locationManager.requestLocationUpdates(NETWORK, 0L, 0f, locationListener)
    }

}
