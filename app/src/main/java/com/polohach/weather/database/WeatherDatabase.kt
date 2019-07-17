package com.polohach.weather.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.polohach.weather.App
import com.polohach.weather.BuildConfig.DB_NAME
import com.polohach.weather.database.converters.LocationTypeConverter
import com.polohach.weather.database.dao.*
import com.polohach.weather.database.tables.*

@Database(entities = [CurrentWeatherDb::class, MainDb::class, WeatherDb::class,
    WindDb::class, CityDb::class], version = DB_VERSION)
@TypeConverters(LocationTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun mainDao(): MainDao

    abstract fun weatherDao(): WeatherDao

    abstract fun windDao(): WindDao

    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(): WeatherDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        App.instance,
                        WeatherDatabase::class.java,
                        DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
