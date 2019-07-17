package com.polohach.weather.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.polohach.weather.database.WEATHER_TABLE
import com.polohach.weather.database.tables.WeatherDb
import io.reactivex.Single

@Dao
interface WeatherDao : BaseDao<WeatherDb> {

    @Query("SELECT * FROM $WEATHER_TABLE WHERE weatherId = :id")
    fun getById(id: Long): Single<WeatherDb>

    @Query("DELETE FROM $WEATHER_TABLE")
    fun deleteAll()
}
