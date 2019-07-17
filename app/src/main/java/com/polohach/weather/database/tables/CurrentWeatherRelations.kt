package com.polohach.weather.database.tables

import androidx.room.Embedded
import androidx.room.Relation


data class CurrentWeatherRelations(@Embedded var currentWeatherDb: CurrentWeatherDb?,
                                   @Embedded var mainDb: MainDb?,
                                   @Relation(parentColumn = "id",
                                           entityColumn = "weatherId",
                                           entity = WeatherDb::class)
                                   var weatherDb: List<WeatherDb>?,
                                   @Embedded var windDb: WindDb?)
