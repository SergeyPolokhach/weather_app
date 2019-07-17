package com.polohach.weather.network.api.converters

import com.polohach.weather.models.Weather
import com.polohach.weather.models.WeatherModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.WeatherBean

interface WeatherBeanConverter : Converter<WeatherBean, Weather>

class WeatherBeanConverterImpl : BaseInConverter<WeatherBean, Weather>(), WeatherBeanConverter {

    override fun processConvertInToOut(inObject: WeatherBean) = inObject.run {
        WeatherModel(id, description, icon, main)
    }
}
