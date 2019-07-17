package com.polohach.weather.models

import kotlinx.android.parcel.Parcelize

interface City : Model<Long> {
    var name: String?
}

@Parcelize
data class CityModel(override var id: Long?,
                     override var name: String?) : City {

    override fun toString() = name ?: ""
}
