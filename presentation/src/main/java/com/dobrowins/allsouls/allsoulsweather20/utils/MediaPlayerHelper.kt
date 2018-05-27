package com.dobrowins.allsouls.allsoulsweather20.utils

import android.content.Context
import android.media.MediaPlayer
import com.dobrowins.allsouls.allsoulsweather20.R
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class MediaPlayerHelper @Inject constructor(private val context: Context) {

    private val soundsArray =
        arrayOf(
            R.raw.zombie_01,
            R.raw.zombie_02,
            R.raw.zombie_03,
            R.raw.zombie_04,
            R.raw.zombie_05,
            R.raw.zombie_06,
            R.raw.zombie_07,
            R.raw.zombie_08,
            R.raw.zombie_09,
            R.raw.zombie_10,
            R.raw.zombie_11,
            R.raw.zombie_12,
            R.raw.zombie_13,
            R.raw.zombie_14,
            R.raw.zombie_15,
            R.raw.zombie_16,
            R.raw.zombie_17,
            R.raw.zombie_18,
            R.raw.zombie_19,
            R.raw.zombie_20,
            R.raw.zombie_21,
            R.raw.zombie_22,
            R.raw.zombie_23,
            R.raw.zombie_24,
            R.raw.zombie_25,
            R.raw.zombie_26,
            R.raw.zombie_27,
            R.raw.zombie_28,
            R.raw.zombie_29,
            R.raw.zombie_30,
            R.raw.zombie_31,
            R.raw.zombie_32
        )

    fun playRawFile() {
        val randomInt = FisherYatesShuffle.getRandomInt(soundsArray.size)
        val soundId = soundsArray[randomInt]
        val mp = MediaPlayer.create(context, soundId)
        mp.start()
        mp.setOnCompletionListener { mediaPlayer ->
            with(mediaPlayer) {
                stop()
                reset()
                release()
            }
        }
    }

}