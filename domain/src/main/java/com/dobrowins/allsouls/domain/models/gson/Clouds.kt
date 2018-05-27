package com.dobrowins.allsouls.domain.models.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */
data class Clouds (
    @SerializedName("all")
    @Expose
    var all: Int? = null
)