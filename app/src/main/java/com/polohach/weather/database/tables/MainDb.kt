package com.polohach.weather.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.polohach.weather.database.MAIN_TABLE

@Entity(tableName = MAIN_TABLE,
        foreignKeys = [(ForeignKey(entity = CurrentWeatherDb::class,
                parentColumns = ["id"],
                childColumns = ["mainId"],
                onDelete = CASCADE))])
data class MainDb(@PrimaryKey var mainId: Long?,
                  val humidity: Int?,
                  val pressure: Int?,
                  val temp: Double?,
                  val tempMax: Double?,
                  val tempMin: Double?)