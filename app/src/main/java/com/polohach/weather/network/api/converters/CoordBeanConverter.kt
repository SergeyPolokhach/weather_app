package com.polohach.weather.network.api.converters

import com.google.android.gms.maps.model.LatLng
import com.polohach.weather.models.converters.BaseInConverter
import com.polohach.weather.models.converters.Converter
import com.polohach.weather.network.api.beans.CoordBean

interface CoordBeanConverter : Converter<CoordBean, LatLng>

class CoordBeanConverterImpl : BaseInConverter<CoordBean, LatLng>(), CoordBeanConverter {

    override fun processConvertInToOut(inObject: CoordBean) = inObject.run {
        LatLng(lat ?: 0.0, lon ?: 0.0)
    }
}
