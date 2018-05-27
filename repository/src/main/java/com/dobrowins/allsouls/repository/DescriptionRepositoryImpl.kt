package com.dobrowins.allsouls.repository

import android.content.res.Resources
import com.dobrowins.allsouls.domain.repositories.DescriptionRepository
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class DescriptionRepositoryImpl
@Inject constructor(resources: Resources) : DescriptionRepository {

    private val defaultDescription by lazy { resources.getString(R.string.all_default_weather_desc) }
    private val thunderstormArray by lazy { resources.getStringArray(R.array.thunderstorm) }
    private val drizzleArray by lazy { resources.getStringArray(R.array.dizzle) }
    private val rainArray by lazy { resources.getStringArray(R.array.rain) }
    private val snowArray by lazy { resources.getStringArray(R.array.snow) }
    private val atmoArray by lazy { resources.getStringArray(R.array.atmosphere) }
    private val extremeArray by lazy { resources.getStringArray(R.array.extreme) }
    private val additionalArray by lazy { resources.getStringArray(R.array.additional) }
    private val cloudArray by lazy { resources.getStringArray(R.array.clouds) }
    private val clearArray by lazy { resources.getStringArray(R.array.clear) }

    override fun getWeatherDescription(weatherId: Int): String =
        when (weatherId) {
            200, 201, 202, 210, 211, 212, 221, 230, 231, 232 -> thunderstormArray[0]
            300, 301, 302, 310, 311 -> drizzleArray[0]
            312, 313, 314, 321 -> drizzleArray[1]
            500, 501 -> rainArray[0]
            502, 503, 504 -> rainArray[1]
            511, 520, 521, 522, 531 -> rainArray[2]
            600, 601 -> snowArray[0]
            602, 622 -> snowArray[1]
            611, 612, 615, 616, 620, 621 -> snowArray[2]
            701, 711, 721 -> atmoArray[0]
            731, 741, 751, 761, 762, 771, 781 -> atmoArray[1]
            800 -> clearArray[0]
            801, 802, 803, 804 -> cloudArray[0]
            900, 901, 902, 903 -> extremeArray[2]
            904 -> extremeArray[0]
            905, 906 -> extremeArray[1]
            951, 952, 953, 954, 955, 956 -> additionalArray[0]
            957, 958, 959 -> additionalArray[1]
            960, 961, 962 -> additionalArray[2]
            else -> defaultDescription
        }
}