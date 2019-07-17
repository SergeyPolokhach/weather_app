package com.polohach.weather.repositories

import com.polohach.weather.database.WeatherDatabase
import com.polohach.weather.database.converters.CurrentWeatherDBConverterImpl
import com.polohach.weather.database.tables.CurrentWeatherRelations
import com.polohach.weather.models.CurrentWeather
import io.reactivex.Single
import io.reactivex.SingleTransformer


interface CurrentWeatherRepository : Repository<CurrentWeather> {

    fun saveCurrentWeather(currentWeather: CurrentWeather): Single<CurrentWeather>

    fun getCurrentWeatherByCityID(cityId: Long): Single<CurrentWeather>
}

class CurrentWeatherRepositoryImpl : BaseRepository<CurrentWeather, CurrentWeatherRelations, Long>(), CurrentWeatherRepository {

    private val currentWeatherDbConverter by lazy { CurrentWeatherDBConverterImpl() }

    override fun saveCurrentWeather(currentWeather: CurrentWeather): Single<CurrentWeather> =
            Single.just(currentWeather)
                    .compose(saveCurrentWeatherWithInnerEntitiesTransformer())

    override fun getCurrentWeatherByCityID(cityId: Long): Single<CurrentWeather> =
            WeatherDatabase.getDatabase().currentWeatherDao().getById(cityId)
                    .compose(currentWeatherDbConverter.singleINtoOUT())

    private fun saveCurrentWeatherWithInnerEntitiesTransformer() = SingleTransformer<CurrentWeather, CurrentWeather> {
        it.flatMap(this::saveCurrentWeatherWithInnerEntities)
    }

    private fun saveCurrentWeatherWithInnerEntities(currentWeather: CurrentWeather) =
            WeatherDatabase.getDatabase().run {
                Single.just(currentWeather)
                        .map { currentWeatherDbConverter.convertOutToIn(it) }
                        .map { relations ->
                            relations.apply { currentWeatherDb?.let { currentWeatherDao().insert(it) } }
                        }
                        .map { relations ->
                            relations.apply { mainDb?.let { mainDao().insert(it) } }
                        }
                        .map { relations ->
                            relations.apply { windDb?.let { windDao().insert(it) } }
                        }
                        .map { relations ->
                            relations.apply { weatherDb?.let { weatherDao().insertAll(*it.toTypedArray()) } }
                        }
                        .map {
                            currentWeather
                        }
            }
}
