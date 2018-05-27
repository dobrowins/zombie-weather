package com.dobrowins.allsouls.allsoulsweather20.views

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dobrowins.allsouls.allsoulsweather20.R
import com.dobrowins.allsouls.allsoulsweather20.di.PresentersFactory
import com.dobrowins.allsouls.allsoulsweather20.presenters.WeatherInfoPresenter
import com.dobrowins.allsouls.domain.models.Weather
import kotlinx.android.synthetic.main.activity_weather_info.*

/**
 * @author Artem Dobrovinskiy
 */
class WeatherInfoActivity : MvpAppCompatActivity(), WeatherInfoView {

    @InjectPresenter
    lateinit var presenter: WeatherInfoPresenter

    @ProvidePresenter
    fun getDaggerPresenter(): WeatherInfoPresenter {
        val presentersFactory = PresentersFactory()
        return presentersFactory.provideWeatherInfoPresenter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        tbWeatherInfo.title = ""
        setSupportActionBar(tbWeatherInfo)

        presenter.onWeatherShown()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        presenter.onMenuCreated(menu)

    override fun createMenu(menu: Menu, intRes: Int) {
        menu.clear()
        menuInflater.inflate(intRes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        presenter.onMetricsChanged()
        return true
    }

    override fun showWeather(weather: Weather) {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        tvWeatherInfoTemperature.startAnimation(fadeIn)
        tvWeatherInfoHumidity.startAnimation(fadeIn)
        tvWeatherInfoDescription.startAnimation(fadeIn)

        val boldFont = Typeface.createFromAsset(
            baseContext.assets,
            "fonts/osans_condenced_bold.ttf"
        )
        val lightFont = Typeface.createFromAsset(
            baseContext.assets,
            "fonts/osans_condenced_light.ttf"
        )
        tvWeatherInfoTemperature.typeface = boldFont
        tvWeatherInfoHumidity.typeface = lightFont
        tvWeatherInfoDescription.typeface = lightFont

        tvWeatherInfoTemperature.text = String.format(
            getString(R.string.currentTemp),
            weather.temperature
        )
        tvWeatherInfoHumidity.text = String.format(
            getString(R.string.currentHumidity),
            weather.humidity
        )
        tvWeatherInfoDescription.text = weather.description
    }

    override fun restartApplication() {
        val splashScreen =
            Intent(this@WeatherInfoActivity, SplashScreenActivity::class.java)
        startActivity(splashScreen)
        finish()
    }

}