package com.polohach.weather.network.api.modules

import com.polohach.weather.models.converters.Converter


abstract class BaseRxModule<T, NetworkModel, M>(val api: T, val converter: Converter<NetworkModel, M>)
