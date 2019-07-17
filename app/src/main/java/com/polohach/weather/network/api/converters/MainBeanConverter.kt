package com.polohach.weather.network.api.converters

import com.polohach.weather.models.Main
import com.polohach.weather.models.MainModel
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.MainBean

interface MainBeanConverter : Converter<MainBean, Main>

class MainBeanConverterImpl : BaseInConverter<MainBean, Main>(), MainBeanConverter {

    override fun processConvertInToOut(inObject: MainBean) = inObject.run {
        MainModel(humidity, pressure, temp, tempMax, tempMin)
    }
}
