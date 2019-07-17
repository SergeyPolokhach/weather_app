package com.polohach.weather.network.api.converters

import com.polohach.weather.models.City
import com.polohach.weather.models.CityModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.CityBean


interface CityBeanConverter : Converter<CityBean, City>

class CityBeanConverterImpl : BaseInConverter<CityBean, City>(), CityBeanConverter {

    override fun processConvertInToOut(inObject: CityBean) = inObject.run {
        CityModel(id, name)
    }
}
