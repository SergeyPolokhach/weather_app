package com.polohach.weather.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.polohach.weather.database.WEATHER_TABLE


@Entity(tableName = WEATHER_TABLE)
data class WeatherDb(@PrimaryKey var weatherId: Long?,
                     val description: String?,
                     val icon: String?,
                     val main: String?)
