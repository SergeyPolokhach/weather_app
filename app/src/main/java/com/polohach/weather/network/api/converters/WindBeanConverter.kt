package com.polohach.weather.network.api.converters

import com.polohach.weather.models.Wind
import com.polohach.weather.models.WindModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.WindBean

interface WindBeanConverter : Converter<WindBean, Wind>

class WindBeanConverterImpl : BaseInConverter<WindBean, Wind>(), WindBeanConverter {

    override fun processConvertInToOut(inObject: WindBean) = inObject.run {
        WindModel(deg, speed)
    }
}
