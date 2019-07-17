package com.polohach.weather.utils.helpers

import com.polohach.weather.R
import com.polohach.weather.enums.OpenWeatherMapKeys
import com.polohach.weather.extensions.getString

class OpenWeatherMapHelper {

    companion object {
        private const val APP_ID = "appId"
    }

    private var options: MutableMap<String, String>? = null

    fun putValue(key: OpenWeatherMapKeys, value: String) = this.apply {
        if (options == null) options = initOption()
        options?.put(key(), value)
    }

    fun build(): Map<String, String> = options.also { options = null } ?: mapOf()

    private fun initOption(): MutableMap<String, String> =
            mutableMapOf(APP_ID to getString(R.string.weather_api_key))
}
