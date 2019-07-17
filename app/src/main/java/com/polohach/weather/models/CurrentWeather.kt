package com.polohach.weather.models

import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

interface CurrentWeather : Model<Long> {
    val base: String?
    val cod: Int?
    val coord: LatLng?
    val dt: Int?
    val main: Main?
    val name: String?
    val timezone: Int?
    val weather: List<Weather>?
    val wind: Wind?
}

@Parcelize
data class CurrentWeatherModel(override val id: Long?,
                               override val base: String?,
                               override val cod: Int?,
                               override val coord: LatLng?,
                               override val dt: Int?,
                               override val main: Main?,
                               override val name: String?,
                               override val timezone: Int?,
                               override val weather: List<Weather>?,
                               override val wind: Wind?) : CurrentWeather
