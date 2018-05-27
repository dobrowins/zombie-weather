@file:Suppress("ReplaceArrayOfWithLiteral")

package com.dobrowins.allsouls.allsoulsweather20.di

import com.dobrowins.allsouls.allsoulsweather20.di.modules.*
import com.dobrowins.allsouls.allsoulsweather20.views.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        AndroidModule::class,
        InteractorsModule::class,
        RepositoryModule::class,
        UtilsModule::class
    )
)
interface AppComponent {
    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(presentersFactory: PresentersFactory)
}