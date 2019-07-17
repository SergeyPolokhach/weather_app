package com.polohach.weather.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.polohach.weather.database.CITY_TABLE


@Entity(tableName = CITY_TABLE)
data class CityDb(@PrimaryKey val id: Long?,
                  val name: String?)
