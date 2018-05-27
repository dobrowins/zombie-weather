package com.dobrowins.allsouls.allsoulsweather20.views

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.allsouls.allsoulsweather20.R
import com.dobrowins.allsouls.allsoulsweather20.di.ObjectGraph
import com.dobrowins.allsouls.allsoulsweather20.di.PresentersFactory
import com.dobrowins.allsouls.allsoulsweather20.presenters.SplashScreenPresenter
import com.dobrowins.allsouls.allsoulsweather20.toast
import com.dobrowins.allsouls.allsoulsweather20.utils.MediaPlayerHelper
import java.lang.ref.WeakReference
import javax.inject.Inject

class SplashScreenActivity : MvpAppCompatActivity(), SplashScreenView {

    @Inject
    lateinit var mediaPlayerHelper: MediaPlayerHelper

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    @ProvidePresenter
    fun getDaggerPresenter(): SplashScreenPresenter {
        val presentersFactory = PresentersFactory()
        return presentersFactory.provideSplashScreenPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        ObjectGraph.appComponent.inject(this@SplashScreenActivity)
        val weakReference =
            WeakReference<Activity>(this@SplashScreenActivity)
        presenter.onPermissionCheck(weakReference)
    }

    override fun startAnimation() {
        val splashScreenImageView = findViewById<ImageView>(R.id.ivZombieIcon)
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate)
        splashScreenImageView.startAnimation(rotate)
        mediaPlayerHelper.playRawFile()
    }

    override fun showPermissionRationale(message: Int) {

        fun requestPermission() {
            val weakReference =
                WeakReference<Activity>(this@SplashScreenActivity)
            presenter.onPermissionCheck(weakReference)
        }

        AlertDialog.Builder(this@SplashScreenActivity)
            .setTitle(message)
            .setPositiveButton(android.R.string.ok, { _, _ ->
                requestPermission()
            })
            .setNegativeButton(android.R.string.cancel, { dialog, _ ->
                // this would lead to error and shutdown ass all the opportunities
                // to request the permission are exhausted
                requestPermission()
            })
            .create()
            .show()
    }

    override fun requestWeather() {
        presenter.onWeatherRequested()
    }

    override fun startWeatherInfoActivity() {
        val weatherInfoActivity =
            Intent(this@SplashScreenActivity, WeatherInfoActivity::class.java)
        startActivity(weatherInfoActivity)
    }

    override fun showErrorAndShutdown(error: Int) {
        toast(error)
        finish()
    }

    override fun showErrorAndShutdown(error: String) {
        toast(error)
        finish()
    }

    override fun showErrorAndStartNextActivity(error: Int) {
        toast(error)
        startWeatherInfoActivity()
    }

}
