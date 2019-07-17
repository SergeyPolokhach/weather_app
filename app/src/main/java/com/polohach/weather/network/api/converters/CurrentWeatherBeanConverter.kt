package com.polohach.weather.network.api.converters

import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.models.CurrentWeatherModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.CurrentWeatherBean

interface CurrentWeatherBeanConverter : Converter<CurrentWeatherBean, CurrentWeather>

class CurrentWeatherBeanConverterImpl : BaseInConverter<CurrentWeatherBean, CurrentWeather>(), CurrentWeatherBeanConverter {

    private val mainBeanConverter by lazy { MainBeanConverterImpl() }
    private val windBeanConverter by lazy { WindBeanConverterImpl() }
    private val weatherBeanConverter by lazy { WeatherBeanConverterImpl() }
    private val coordBeanConverter by lazy { CoordBeanConverterImpl() }

    override fun processConvertInToOut(inObject: CurrentWeatherBean) = inObject.run {
        CurrentWeatherModel(
                id,
                base,
                cod,
                coord?.let { coordBeanConverter.convertInToOut(it) },
                dt,
                main?.let { mainBeanConverter.convertInToOut(it) },
                name,
                timezone,
                weather?.let { weatherBeanConverter.convertListInToOut(it) },
                wind?.let { windBeanConverter.convertInToOut(it) })
    }
}
