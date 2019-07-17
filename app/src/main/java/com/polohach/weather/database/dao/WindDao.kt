package com.polohach.weather.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.polohach.weather.database.WIND_TABLE
import com.polohach.weather.database.tables.WindDb
import io.reactivex.Single

@Dao
interface WindDao : BaseDao<WindDb> {

    @Query("SELECT * FROM $WIND_TABLE WHERE windId = :id")
    fun getById(id: Long): Single<WindDb>

    @Query("DELETE FROM $WIND_TABLE")
    fun deleteAll()
}
