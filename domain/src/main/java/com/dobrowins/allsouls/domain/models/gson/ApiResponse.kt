package com.dobrowins.allsouls.domain.models.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */
data class ApiResponse(

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null,

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null,

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null,

    @SerializedName("main")
    @Expose
    var main: Main? = null,

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null,

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null,

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null,

    @SerializedName("dt")
    @Expose
    var dt: Int? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("cod")
    @Expose
    var cod: Int? = null

)
