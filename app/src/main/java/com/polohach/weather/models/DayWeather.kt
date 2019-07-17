package com.polohach.weather.models

import kotlinx.android.parcel.Parcelize

interface DayWeather : Model<Long> {
    var main: Main?
    var wind: Wind?
    var weather: List<Weather>?
    var date: String?
}


@Parcelize
data class DayWeatherModel(override val id: Long?,
                           override var main: Main?,
                           override var wind: Wind?,
                           override var weather: List<Weather>?,
                           override var date: String?) : DayWeather
