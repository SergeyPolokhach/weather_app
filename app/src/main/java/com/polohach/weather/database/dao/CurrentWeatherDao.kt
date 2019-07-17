package com.polohach.weather.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.google.android.gms.maps.model.LatLng
import com.polohach.weather.database.CURRENT_WEATHER_TABLE
import com.polohach.weather.database.MAIN_TABLE
import com.polohach.weather.database.WEATHER_TABLE
import com.polohach.weather.database.WIND_TABLE
import com.polohach.weather.database.tables.CurrentWeatherDb
import com.polohach.weather.database.tables.CurrentWeatherRelations
import io.reactivex.Single

@Dao
interface CurrentWeatherDao : BaseDao<CurrentWeatherDb> {

    @Query("SELECT * FROM $CURRENT_WEATHER_TABLE," +
            " $MAIN_TABLE, $WEATHER_TABLE, $WIND_TABLE" +
            " WHERE $CURRENT_WEATHER_TABLE.id = :id" +
            " AND $MAIN_TABLE.mainId = :id" +
            " AND $WEATHER_TABLE.weatherId = :id" +
            " AND $WIND_TABLE.windId = :id")
    fun getById(id: Long): Single<CurrentWeatherRelations>

    @Query("SELECT * FROM $CURRENT_WEATHER_TABLE" +
            " WHERE $CURRENT_WEATHER_TABLE.coord = :coord")
    fun getByLocation(coord: LatLng): Single<CurrentWeatherRelations>

    @Query("DELETE FROM $CURRENT_WEATHER_TABLE")
    fun deleteAll()
}

