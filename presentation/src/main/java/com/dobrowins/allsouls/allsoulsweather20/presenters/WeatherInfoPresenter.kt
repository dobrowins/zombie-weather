package com.dobrowins.allsouls.allsoulsweather20.presenters

import android.view.Menu
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.allsouls.allsoulsweather20.R
import com.dobrowins.allsouls.allsoulsweather20.views.WeatherInfoView
import com.dobrowins.allsouls.domain.SchedulersFactory
import com.dobrowins.allsouls.domain.WeatherInfoInteractor
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class WeatherInfoPresenter
@Inject constructor(
    private val interactor: WeatherInfoInteractor,
    private val schedulers: SchedulersFactory
) : BasePresenter<WeatherInfoView>() {

    fun onWeatherShown() {
        interactor.getLastFetchedWeather()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(viewState::showWeather)
    }

    fun onMenuCreated(menu: Menu?): Boolean {
        val isMetric = interactor.isUnitsMetric()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .blockingGet()
        val menuRes = if (isMetric) R.menu.menu_metric else R.menu.menu_imperial
        viewState.createMenu(menu as Menu, menuRes)
        return true
    }

    fun onMetricsChanged() {
        interactor.applyMetricsChanged()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnComplete { viewState.restartApplication() }
            .subscribe()
    }

}