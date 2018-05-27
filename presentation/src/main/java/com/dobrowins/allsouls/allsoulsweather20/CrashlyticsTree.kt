package com.dobrowins.allsouls.allsoulsweather20

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import android.util.Log
import dagger.Provides
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Artem Dobrovinskiy
 */
class CrashlyticsTree : Timber.Tree() {
//    companion object {
//        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
//        private const val CRASHLYTICS_KEY_TAG = "tag"
//        private const val CRASHLYTICS_KEY_MESSAGE = "message"
//    }
//
    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
//        when (priority) {
//            Log.VERBOSE or Log.DEBUG -> {
//                return
//            }
//            Log.INFO -> {
//                Crashlytics.log(message)
//            }
//            Log.ASSERT, Log.WARN, Log.ERROR -> {
//                Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority)
//                Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag)
//                Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message)
//                throwable?.let(Crashlytics::logException)
//                        ?: Crashlytics.logException(Exception(message))
//            }
//        }
    }
}