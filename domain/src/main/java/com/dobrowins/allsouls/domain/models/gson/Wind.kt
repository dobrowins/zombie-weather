package com.dobrowins.allsouls.domain.models.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */
data class Wind(
    @SerializedName("speed")
    @Expose
    var speed: Double? = null,

    @SerializedName("deg")
    @Expose
    var deg: Double? = null
)