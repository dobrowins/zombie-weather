package com.dobrowins.allsouls.allsoulsweather20.views

import com.arellomobile.mvp.MvpView

/**
 * @author Artem Dobrovinskiy
 */
interface SplashScreenView : MvpView {
    fun startAnimation()
    fun showPermissionRationale(message: Int)
    fun startWeatherInfoActivity()
    fun requestWeather()
    fun showErrorAndStartNextActivity(error: Int)
    fun showErrorAndShutdown(error: String)
    fun showErrorAndShutdown(error: Int)
}