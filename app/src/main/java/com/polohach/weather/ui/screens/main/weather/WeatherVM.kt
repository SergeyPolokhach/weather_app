package com.polohach.weather.ui.screens.main.weather

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.cleveroad.bootstrap.kotlin_core.ui.NO_ID
import com.google.android.gms.maps.model.LatLng
import com.polohach.weather.R
import com.polohach.weather.enums.OpenWeatherMapKeys
import com.polohach.weather.enums.Units
import com.polohach.weather.extensions.getString
import com.polohach.weather.models.City
import com.polohach.weather.models.CityModel
import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.providers.ProviderInjector
import com.polohach.weather.ui.base.BaseVM
import com.polohach.weather.utils.helpers.OpenWeatherMapHelper
import io.reactivex.functions.Consumer

class WeatherVM(app: Application) : BaseVM(app) {

    companion object {
        private val myLocation = CityModel(NO_ID.toLong(), getString(R.string.my_location))
        private val defaultCity = CityModel(703448, getString(R.string.default_city_name))
    }

    val currentWeatherLD = MutableLiveData<CurrentWeather>()
    val noLocalWeatherLD = MutableLiveData<Throwable>()
    val citiesLD = MutableLiveData<List<City>>()

    fun getMyCities() {
        ProviderInjector.city.getLocaleCities()
                .map { it.toMutableList() }
                .map {
                    it.apply {
                        if (isEmpty()) add(defaultCity)
                        add(myLocation)
                    }
                }
                .map { it.toList() }
                .doAsync(citiesLD)
    }

    fun getCurrentWeather(cityId: Long) {
        getOnlineCurrentWeather(cityId)
        getLocalCurrentWeather(cityId)
    }

    fun getOnlineCurrentWeatherByGeoCoordinates(latLng: LatLng) {
        val options = OpenWeatherMapHelper()
                .putValue(OpenWeatherMapKeys.UNITS, Units.CELSIUS())
                .putValue(OpenWeatherMapKeys.LATITUDE, latLng.latitude.toString())
                .putValue(OpenWeatherMapKeys.LONGITUDE, latLng.longitude.toString())
                .build()

        ProviderInjector.currentWeather.getCurrentWeatherByGeoCoordinates(options)
                .doAsync(currentWeatherLD)
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
