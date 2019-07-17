package com.polohach.weather.ui.screens.main.add_city

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.polohach.weather.models.City
import com.polohach.weather.providers.ProviderInjector
import com.polohach.weather.ui.base.BaseVM
import io.reactivex.Single

class AddCityVM(app: Application) : BaseVM(app) {

    val filteredCitiesLD = MutableLiveData<List<City>>()
    val savedCitiesLD = MutableLiveData<List<City>>()
    val saveCityLD = MutableLiveData<City>()
    private val citiesLD = MutableLiveData<List<City>>()

    fun getCities() {
        ProviderInjector.city.getCities()
                .doAsync(citiesLD)
    }

    fun filterCity(constraint: CharSequence) {
        Single.just(citiesLD.value ?: listOf())
                .map { cities -> cities.filter { it.name?.contains(constraint, ignoreCase = true) == true } }
                .doAsync(filteredCitiesLD, isShowProgress = false)
    }

    fun saveCity(city: City) {
        ProviderInjector.city.saveCity(city)
                .doAsync(saveCityLD)
    }

    fun getMyCities() {
        ProviderInjector.city.getLocaleCities()
                .doAsync(savedCitiesLD)
    }
}
