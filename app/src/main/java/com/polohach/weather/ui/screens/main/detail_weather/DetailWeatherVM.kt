package com.polohach.weather.ui.screens.main.detail_weather

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.polohach.weather.enums.OpenWeatherMapKeys
import com.polohach.weather.enums.Units
import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.models.ForecastWeather
import com.polohach.weather.providers.ProviderInjector
import com.polohach.weather.ui.base.BaseVM
import com.polohach.weather.utils.helpers.OpenWeatherMapHelper
import io.reactivex.functions.Consumer

class DetailWeatherVM(app: Application) : BaseVM(app) {

    val forecastWeatherLD = MutableLiveData<ForecastWeather>()
    val currentWeatherLD = MutableLiveData<CurrentWeather>()
    val noLocalWeatherLD = MutableLiveData<Throwable>()

    fun getForecastWeather(cityId: Long?) {
        cityId?.let { getNetworkCurrentWeather(it) }
    }

    fun getCurrentWeather(cityId: Long?) {
        cityId?.let {
            getOnlineCurrentWeather(it)
            getLocalCurrentWeather(it)
        }
    }

    private fun getNetworkCurrentWeather(cityId: Long) {
        val options = OpenWeatherMapHelper()
                .putValue(OpenWeatherMapKeys.UNITS, Units.CELSIUS())
                .putValue(OpenWeatherMapKeys.CITY_ID, cityId.toString())
                .build()

        ProviderInjector.forecastWeather.getForecastWeatherByCityID(options)
                .doAsync(forecastWeatherLD)
    }

    private fun getOnlineCurrentWeather(cityId: Long) {
        val options = OpenWeatherMapHelper()
                .putValue(OpenWeatherMapKeys.UNITS, Units.CELSIUS())
                .putValue(OpenWeatherMapKeys.CITY_ID, cityId.toString())
                .build()

        ProviderInjector.currentWeather.getCurrentWeatherByCityID(options)
                .doAsync(currentWeatherLD)
    }

    private fun getLocalCurrentWeather(cityId: Long) {
        ProviderInjector.currentWeather.getLocalCurrentWeatherByCityID(cityId)
                .doAsync(currentWeatherLD, Consumer { noLocalWeatherLD.value = it })
    }
}
