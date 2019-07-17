package com.polohach.weather.models.converters

abstract class BaseInConverter<IN : Any, OUT : Any> : BaseConverter<IN, OUT>() {

    @Suppress("UNCHECKED_CAST")
    override fun processConvertOutToIn(outObject: OUT): IN = Any() as IN
}