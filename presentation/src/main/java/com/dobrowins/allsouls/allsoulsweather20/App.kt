@file:Suppress("DEPRECATION")

package com.dobrowins.allsouls.allsoulsweather20

import android.app.Application
import com.dobrowins.allsouls.allsoulsweather20.di.ObjectGraph
import timber.log.Timber

/**
 * @author Artem Dobrovinskiy
 */
class App : Application() {

//    companion object {
//        @JvmStatic
//        @set:VisibleForTesting
//        lateinit var appComponent: AppComponent
//    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
    }

    private fun initDagger() {
        ObjectGraph.create(this@App)
//        appComponent = DaggerAppComponent.builder()
//            .androidModule(AndroidModule(this@App))
//            .build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}