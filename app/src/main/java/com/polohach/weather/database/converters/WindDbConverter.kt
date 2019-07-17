package com.polohach.weather.database.converters

import com.polohach.weather.database.tables.WindDb
import com.polohach.weather.models.Wind
import com.polohach.weather.models.WindModel
import com.polohach.weather.models.converters.BaseConverter
import com.polohach.weather.models.converters.Converter

interface WindDbConverter : Converter<WindDb, Wind>

class WindDbConverterImpl : BaseConverter<WindDb, Wind>(), WindDbConverter {

    override fun processConvertInToOut(inObject: WindDb) = inObject.run {
        WindModel(deg, speed)
    }

    override fun processConvertOutToIn(outObject: Wind) = outObject.run {
        WindDb(0, deg, speed)
    }
}
