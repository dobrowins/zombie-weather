package com.dobrowins.allsouls.allsoulsweather20.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import com.dobrowins.allsouls.allsoulsweather20.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
@Module
class AndroidModule(private val application: App) {

    @Provides
    @Singleton
    fun appContext(): Context = application

    @Provides
    @Singleton
    fun resources(): Resources = application.resources

    @Provides
    @Singleton
    fun defaultSharedPref(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

}