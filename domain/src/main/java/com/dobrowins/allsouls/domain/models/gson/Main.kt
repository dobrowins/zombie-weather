package com.dobrowins.allsouls.domain.models.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */
data class Main(

    @SerializedName("temp")
    @Expose
    var temp: Double? = null,

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null,

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null,

    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null,

    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null

)