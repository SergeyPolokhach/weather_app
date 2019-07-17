package com.polohach.weather.models

import kotlinx.android.parcel.Parcelize

interface ForecastWeather : Model<Long> {
    val city: City?
    val daysWeather: List<DayWeather>?
}

@Parcelize
data class ForecastWeatherModel(override val id: Long?,
                                override val city: City?,
                                override val daysWeather: List<DayWeather>?) : ForecastWeather
