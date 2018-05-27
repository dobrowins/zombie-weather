package com.dobrowins.allsouls.repository

import android.content.SharedPreferences
import com.dobrowins.allsouls.domain.repositories.KeyValueStorage
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class KeyValueStorageImpl
@Inject constructor(
    private val sharedPreferences: SharedPreferences
) : KeyValueStorage {

    override fun putStringValue(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    override fun getStringValue(key: String, value: String?): String? =
        sharedPreferences.getString(key, null)

}
