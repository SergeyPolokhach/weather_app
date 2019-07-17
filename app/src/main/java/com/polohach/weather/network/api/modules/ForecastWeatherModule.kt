package com.polohach.weather.network.api.modules

import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.models.ForecastWeather
import com.polohach.weather.network.api.beans.CurrentWeatherBean
import com.polohach.weather.network.api.converters.CurrentWeatherBeanConverterImpl
import com.polohach.weather.network.api.converters.ForecastWeatherBeanConverterImpl
import com.polohach.weather.network.api.retrofit.ForecastWeatherApi
import io.reactivex.Single


interface ForecastWeatherModule {

    fun getForecastWeatherByCityID(options: Map<String, String>): Single<ForecastWeather>
}

class ForecastWeatherModuleImpl(api: ForecastWeatherApi) :
        BaseRxModule<ForecastWeatherApi, CurrentWeatherBean, CurrentWeather>(api, CurrentWeatherBeanConverterImpl()), ForecastWeatherModule {

    private val forecastWeatherBeanConverter by lazy { ForecastWeatherBeanConverterImpl() }

    override fun getForecastWeatherByCityID(options: Map<String, String>): Single<ForecastWeather> =
            api.getForecastWeatherByCityID(options)
                    .onErrorResumeNext(NetworkErrorUtils.rxParseSingleError())
                    .compose(forecastWeatherBeanConverter.singleINtoOUT())
}
