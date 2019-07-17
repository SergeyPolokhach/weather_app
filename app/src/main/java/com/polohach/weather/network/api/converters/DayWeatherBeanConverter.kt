package com.polohach.weather.network.api.converters

import com.polohach.weather.models.DayWeather
import com.polohach.weather.models.DayWeatherModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.DayWeatherBean

interface DayWeatherBeanConverter : Converter<DayWeatherBean, DayWeather>

class DayWeatherBeanConverterImpl : BaseInConverter<DayWeatherBean, DayWeather>(), DayWeatherBeanConverter {

    private val weatherBeanConverter by lazy { WeatherBeanConverterImpl() }
    private val mainBeanConverter by lazy { MainBeanConverterImpl() }
    private val windBeanConverter by lazy { WindBeanConverterImpl() }

    override fun processConvertInToOut(inObject: DayWeatherBean) = inObject.run {
        DayWeatherModel(
                0,
                main?.let { mainBeanConverter.convertInToOut(it) },
                wind?.let { windBeanConverter.convertInToOut(it) },
                weather?.let { weatherBeanConverter.convertListInToOut(it) },
                date)
    }
}
