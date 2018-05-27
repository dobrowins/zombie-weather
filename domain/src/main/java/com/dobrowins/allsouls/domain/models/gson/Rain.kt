package com.dobrowins.allsouls.domain.models.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */
data class Rain(

    @SerializedName("3h")
    @Expose
    var _3h: Int? = null

)