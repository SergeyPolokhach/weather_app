package com.polohach.weather.database.converters

import com.polohach.weather.database.tables.CityDb
import com.polohach.weather.models.City
import com.polohach.weather.models.CityModel
import com.polohach.weather.models.converters.BaseConverter
import com.polohach.weather.models.converters.Converter

interface CityDbConverter : Converter<CityDb, City>

class CityDbConverterImpl : BaseConverter<CityDb, City>(), CityDbConverter {

    override fun processConvertInToOut(inObject: CityDb) = inObject.run {
        CityModel(id, name)
    }

    override fun processConvertOutToIn(outObject: City) = outObject.run {
        CityDb(id, name)
    }
}
