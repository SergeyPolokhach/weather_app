package com.polohach.weather.models

import android.os.Parcelable

interface Model<T> : Parcelable {

    val id: T?
}
