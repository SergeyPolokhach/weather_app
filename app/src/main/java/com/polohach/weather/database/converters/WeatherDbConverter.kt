package com.polohach.weather.database.converters

import com.polohach.weather.database.tables.WeatherDb
import com.polohach.weather.models.Weather
import com.polohach.weather.models.WeatherModel
import com.polohach.weather.models.converters.BaseConverter
import com.polohach.weather.models.converters.Converter

interface WeatherDbConverter : Converter<WeatherDb, Weather>

class WeatherDbConverterImpl : BaseConverter<WeatherDb, Weather>(), WeatherDbConverter {

    override fun processConvertInToOut(inObject: WeatherDb) = inObject.run {
        WeatherModel(weatherId, description, icon, main)
    }

    override fun processConvertOutToIn(outObject: Weather) = outObject.run {
        WeatherDb(0, description, icon, main)
    }
}
