package com.polohach.weather.repositories

import com.polohach.weather.database.WeatherDatabase
import com.polohach.weather.database.converters.CityDbConverterImpl
import com.polohach.weather.database.tables.CityDb
import com.polohach.weather.models.City
import io.reactivex.Single

interface CityRepository : Repository<City> {

    fun saveCity(city: City): Single<City>

    fun getCities(): Single<List<City>>
}

class CityRepositoryImpl : BaseRepository<City, CityDb, Long>(), CityRepository {

    private val cityDbConverter by lazy { CityDbConverterImpl() }


    override fun saveCity(city: City): Single<City> = WeatherDatabase.getDatabase().run {
        Single.just(city)
                .map { cityDbConverter.convertOutToIn(it) }
                .map { cityDao().insert(it) }
                .map { city }
    }

    override fun getCities(): Single<List<City>> = WeatherDatabase.getDatabase().run {
        cityDao().getAllCities()
                .map { cityDbConverter.convertListInToOut(it) }
    }
}
