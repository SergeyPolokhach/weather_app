package com.polohach.weather.database.converters

import com.polohach.weather.database.tables.CurrentWeatherDb
import com.polohach.weather.database.tables.CurrentWeatherRelations
import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.models.CurrentWeatherModel
import com.polohach.weather.models.converters.BaseConverter
import com.polohach.weather.models.converters.Converter

interface CurrentWeatherDBConverter : Converter<CurrentWeatherRelations, CurrentWeather>

class CurrentWeatherDBConverterImpl : BaseConverter<CurrentWeatherRelations, CurrentWeather>(), CurrentWeatherDBConverter {

    private val mainDbConverter by lazy { MainDbConverterImpl() }
    private val windDbConverter by lazy { WindDbConverterImpl() }
    private val weatherDbConverter by lazy { WeatherDbConverterImpl() }

    override fun processConvertInToOut(inObject: CurrentWeatherRelations) = inObject.run {
        CurrentWeatherModel(
                currentWeatherDb?.id,
                currentWeatherDb?.base,
                currentWeatherDb?.cod,
                currentWeatherDb?.coord,
                currentWeatherDb?.dt,
                mainDb?.let { mainDbConverter.convertInToOut(it) },
                currentWeatherDb?.name,
                currentWeatherDb?.timezone,
                weatherDb?.let { weatherDbConverter.convertListInToOut(it) },
                windDb?.let { windDbConverter.convertInToOut(it) })
    }

    override fun processConvertOutToIn(outObject: CurrentWeather) = outObject.run {
        CurrentWeatherRelations(
                CurrentWeatherDb(id, base, cod, coord, dt, name, timezone),
                main?.let { mainDbConverter.convertOutToIn(it) }?.apply { mainId = id },
                weather?.let { weatherDbConverter.convertListOutToIn(it) }?.apply {
                    forEach {
                        it.weatherId = id
                    }
                },
                wind?.let { windDbConverter.convertOutToIn(it) }?.apply { windId = id })
    }
}
