package com.dobrowins.allsouls.allsoulsweather20.views

import android.view.Menu
import com.arellomobile.mvp.MvpView
import com.dobrowins.allsouls.domain.models.Weather

/**
 * @author Artem Dobrovinskiy
 */
interface WeatherInfoView : MvpView {
    fun createMenu(menu: Menu, intRes: Int)
    fun showWeather(weather: Weather)
    fun restartApplication()
}