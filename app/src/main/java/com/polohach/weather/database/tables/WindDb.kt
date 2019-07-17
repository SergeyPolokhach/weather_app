package com.polohach.weather.database.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.polohach.weather.database.WIND_TABLE

@Entity(tableName = WIND_TABLE,
        foreignKeys = [(ForeignKey(entity = CurrentWeatherDb::class,
                parentColumns = ["id"],
                childColumns = ["windId"],
                onDelete = CASCADE))])
data class WindDb(@PrimaryKey var windId: Long?,
                  val deg: Double?,
                  val speed: Double?)
