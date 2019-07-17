package com.polohach.weather.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


interface Wind : Parcelable {
    val deg: Double?
    val speed: Double?
}

@Parcelize
data class WindModel(override val deg: Double?,
                     override val speed: Double?) : Wind
