@file:Suppress("MemberVisibilityCanBePrivate")

package com.dobrowins.allsouls.allsoulsweather20.di

import android.app.Activity
import com.dobrowins.allsouls.allsoulsweather20.presenters.SplashScreenPresenter
import com.dobrowins.allsouls.allsoulsweather20.presenters.WeatherInfoPresenter
import com.dobrowins.allsouls.domain.PermissionWrapper
import com.dobrowins.allsouls.domain.SchedulersFactory
import com.dobrowins.allsouls.domain.SplashScreenInteractor
import com.dobrowins.allsouls.domain.WeatherInfoInteractor
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class PresentersFactory {

    init {
        ObjectGraph.appComponent.inject(this@PresentersFactory)
    }

    @Inject
    lateinit var splashScreenInteractor: SplashScreenInteractor

    @Inject
    lateinit var weatherInfoInteractor: WeatherInfoInteractor

    @Inject
    lateinit var permissionWrapper: PermissionWrapper<Activity, Permission>

    @Inject
    lateinit var schedulers: SchedulersFactory

    fun provideSplashScreenPresenter(): SplashScreenPresenter =
        SplashScreenPresenter(
            splashScreenInteractor,
            permissionWrapper,
            schedulers
        )

    fun provideWeatherInfoPresenter(): WeatherInfoPresenter =
        WeatherInfoPresenter(
            weatherInfoInteractor,
            schedulers
        )
}