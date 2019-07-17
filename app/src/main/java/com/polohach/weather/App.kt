package com.polohach.weather

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.polohach.weather.database.WeatherDatabase
import com.securepreferences.SecurePreferences

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
        lateinit var prefs: SharedPreferences
            private set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        WeatherDatabase.getDatabase()
        prefs = if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            getSharedPreferences()
        } else {
            getSecurePreferences()
        }
    }

    private fun getSharedPreferences() = instance.applicationContext
            .getSharedPreferences(BuildConfig.SECURE_PREF_NAME, Context.MODE_PRIVATE)

    private fun getSecurePreferences() = SecurePreferences(this,
            BuildConfig.SECURE_PREF_PASSWORD,
            BuildConfig.SECURE_PREF_NAME)
}
