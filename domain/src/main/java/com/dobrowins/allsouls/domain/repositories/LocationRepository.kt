package com.dobrowins.allsouls.domain.repositories

import com.dobrowins.allsouls.domain.models.Coordinates
import io.reactivex.Single

/**
 * @author Artem Dobrovinskiy
 */
interface LocationRepository {
    fun getLastKnownLocation(): Single<Coordinates>
    fun storeLocation(coordinates: Coordinates): Single<Coordinates>
    fun getLocationUpdates()
}