package com.polohach.weather.database.converters

import com.polohach.weather.database.tables.MainDb
import com.polohach.weather.models.Main
import com.polohach.weather.models.MainModel
import com.polohach.weather.models.converters.BaseConverter
import com.polohach.weather.models.converters.Converter

interface MainDbConverter : Converter<MainDb, Main>

class MainDbConverterImpl : BaseConverter<MainDb, Main>(), MainDbConverter {

    override fun processConvertInToOut(inObject: MainDb) = inObject.run {
        MainModel(humidity, pressure, temp, tempMax, tempMin)
    }

    override fun processConvertOutToIn(outObject: Main) = outObject.run {
        MainDb(0, humidity, pressure, temp, tempMax, tempMin)
    }
}
