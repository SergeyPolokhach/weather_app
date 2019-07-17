package com.polohach.weather.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.polohach.weather.database.CITY_TABLE
import com.polohach.weather.database.tables.CityDb
import io.reactivex.Single

@Dao
interface CityDao : BaseDao<CityDb> {

    @Query("SELECT * FROM $CITY_TABLE")
    fun getAllCities(): Single<List<CityDb>>

    @Query("DELETE FROM $CITY_TABLE")
    fun deleteAll()
}
