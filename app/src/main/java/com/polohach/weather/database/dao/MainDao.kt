package com.polohach.weather.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.polohach.weather.database.MAIN_TABLE
import com.polohach.weather.database.tables.MainDb
import io.reactivex.Single

@Dao
interface MainDao : BaseDao<MainDb> {

    @Query("SELECT * FROM $MAIN_TABLE WHERE mainId = :id")
    fun getById(id: Long): Single<MainDb>

    @Query("DELETE FROM $MAIN_TABLE")
    fun deleteAll()
}
