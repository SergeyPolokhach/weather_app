package com.polohach.weather.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.polohach.weather.database.CURRENT_WEATHER_TABLE

@Entity(tableName = CURRENT_WEATHER_TABLE)
data class CurrentWeatherDb(@PrimaryKey var id: Long?,
                            var base: String?,
                            var cod: Int?,
                            var coord: LatLng?,
                            var dt: Int?,
                            var name: String?,
                            var timezone: Int?)
