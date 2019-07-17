package com.polohach.weather.models

import kotlinx.android.parcel.Parcelize

interface Weather : Model<Long?> {
    val description: String?
    val icon: String?
    val main: String?
}

@Parcelize
data class WeatherModel(override val id: Long?,
                        override val description: String?,
                        override val icon: String?,
                        override val main: String?) : Weather
