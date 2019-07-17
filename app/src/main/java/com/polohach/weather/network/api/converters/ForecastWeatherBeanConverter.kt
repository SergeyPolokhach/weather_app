package com.polohach.weather.network.api.converters

import com.polohach.weather.models.ForecastWeather
import com.polohach.weather.models.ForecastWeatherModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.ForecastWeatherBean

interface ForecastWeatherBeanConverter : Converter<ForecastWeatherBean, ForecastWeather>

class ForecastWeatherBeanConverterImpl : BaseInConverter<ForecastWeatherBean, ForecastWeather>(), ForecastWeatherBeanConverter {

    private val cityBeanConverter by lazy { CityBeanConverterImpl() }
    private val dayWeatherBeanConverter by lazy { DayWeatherBeanConverterImpl() }

    override fun processConvertInToOut(inObject: ForecastWeatherBean) = inObject.run {
        ForecastWeatherModel(
                city?.id,
                city?.let { cityBeanConverter.convertInToOut(it) },
                weathers?.let { dayWeatherBeanConverter.convertListInToOut(it) })
    }
}
