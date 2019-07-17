package com.polohach.weather.models.converters

abstract class BaseOutConverter<IN : Any, OUT : Any> : BaseConverter<IN, OUT>() {

    @Suppress("UNCHECKED_CAST")
    override fun processConvertInToOut(inObject: IN): OUT = Any() as OUT
}