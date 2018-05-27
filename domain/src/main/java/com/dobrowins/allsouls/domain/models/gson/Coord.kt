package com.dobrowins.allsouls.domain.models.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */
data class Coord(

    @SerializedName("lon")
    @Expose
    var lon: Int? = null,

    @SerializedName("lat")
    @Expose
    var lat: Int? = null

)