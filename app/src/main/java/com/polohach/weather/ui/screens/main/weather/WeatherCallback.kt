package com.polohach.weather.ui.screens.main.weather

interface WeatherCallback {
    fun openAddCity()
    fun openDetailWeather(cityId: Long)
}
