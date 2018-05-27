package com.dobrowins.allsouls.domain.repositories

/**
 * @author Artem Dobrovinskiy
 */
interface KeyValueStorage {
    fun putStringValue(key: String, value: String)
    fun getStringValue(key: String, value: String?): String?
}