package com.dobrowins.allsouls.allsoulsweather20.di

import com.dobrowins.allsouls.allsoulsweather20.App
import com.dobrowins.allsouls.allsoulsweather20.OpenForTesting
import com.dobrowins.allsouls.allsoulsweather20.di.modules.*

/**
 * @author Artem Dobrovinskiy
 */
object ObjectGraph {

    @JvmStatic
    @set:OpenForTesting
    lateinit var appComponent: AppComponent

    fun create(context: App) {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .androidModule(AndroidModule(context))
            .repositoryModule(RepositoryModule())
            .interactorsModule(InteractorsModule())
            .utilsModule(UtilsModule())
            .build()
    }

}