package com.polohach.weather.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


interface Main : Parcelable {
    val humidity: Int?
    val pressure: Int?
    val temp: Double?
    val tempMax: Double?
    val tempMin: Double?
}

@Parcelize
data class MainModel(override val humidity: Int?,
                     override val pressure: Int?,
                     override val temp: Double?,
                     override val tempMax: Double?,
                     override val tempMin: Double?) : Main
