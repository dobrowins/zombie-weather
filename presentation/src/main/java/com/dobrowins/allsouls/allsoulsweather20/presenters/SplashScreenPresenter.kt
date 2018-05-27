package com.dobrowins.allsouls.allsoulsweather20.presenters

import android.Manifest
import android.app.Activity
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.allsouls.allsoulsweather20.R
import com.dobrowins.allsouls.allsoulsweather20.views.SplashScreenView
import com.dobrowins.allsouls.domain.PermissionWrapper
import com.dobrowins.allsouls.domain.SchedulersFactory
import com.dobrowins.allsouls.domain.SplashScreenInteractor
import com.tbruyelle.rxpermissions2.Permission
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class SplashScreenPresenter @Inject constructor(
    private val interactor: SplashScreenInteractor,
    private val permissionWrapper: PermissionWrapper<Activity, Permission>,
    private val schedulers: SchedulersFactory
) : BasePresenter<SplashScreenView>() {

    companion object {
        private const val PERMISSION_FINE_LOCATION =
            Manifest.permission.ACCESS_FINE_LOCATION
    }

    init {
        viewState.startAnimation()
    }

    fun onPermissionCheck(weakReference: WeakReference<Activity>) {
        permissionWrapper.create(weakReference)
        permissionWrapper.isPermissionGranted(PERMISSION_FINE_LOCATION)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.computation())
            .subscribe { hasPermission ->
                when (hasPermission) {
                    true -> {
                        permissionWrapper.clearReferent()
                        onNetworkAccessibleCheck()
                    }
                    false -> {
                        requestPermission(PERMISSION_FINE_LOCATION)
                    }
                }
            }
            .autoDispose()
    }

    private fun requestPermission(permissionName: String) {
        permissionWrapper.request(permissionName)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.ui())
            .subscribe { permission ->
                when {
                    permission.granted -> {
                        permissionWrapper.clearReferent()
                        onNetworkAccessibleCheck()
                    }
                    permission.shouldShowRequestPermissionRationale -> {
                        viewState.showPermissionRationale(R.string.all_rationale_location)
                    }
                    else -> {
                        viewState.showErrorAndStartNextActivity(R.string.all_error_no_permission_granted)
                    }
                }
            }
            .autoDispose()
    }

    private fun onNetworkAccessibleCheck() {
        interactor.isNetworkAccessible()
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.ui())
            .subscribe { hasNetwork ->
                when (hasNetwork) {
                    true -> viewState.requestWeather()
                    false ->
                        // if no network is accessible we still starting next activity -
                        // where last persisted data would be shown
                        viewState.showErrorAndStartNextActivity(R.string.all_error_no_network_accessible)
                }
            }
            .autoDispose()
    }

    fun onWeatherRequested() {
        interactor.storeDefaultUnits()
            .andThen(interactor.requestAndStoreWeather())
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnComplete { viewState.startWeatherInfoActivity() }
            .doOnError(::onErrorShutdown)
            .subscribe()
            .autoDispose()
    }

    private fun onErrorShutdown(t: Throwable) =
        viewState.showErrorAndShutdown(t.message.toString())

}