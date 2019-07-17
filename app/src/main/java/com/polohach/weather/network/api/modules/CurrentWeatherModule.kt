package com.polohach.weather.network.api.modules

import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.network.api.beans.CurrentWeatherBean
import com.polohach.weather.network.api.converters.CurrentWeatherBeanConverterImpl
import com.polohach.weather.network.api.converters.ForecastWeatherBeanConverterImpl
import com.polohach.weather.network.api.retrofit.CurrentWeatherApi
import io.reactivex.Single


interface CurrentWeatherModule {

    fun getCurrentWeatherByCityID(options: Map<String, String>): Single<CurrentWeather>

    fun getCurrentWeatherByGeoCoordinates(options: Map<String, String>): Single<CurrentWeather>
}

class CurrentWeatherModuleImpl(api: CurrentWeatherApi) :
        BaseRxModule<CurrentWeatherApi, CurrentWeatherBean, CurrentWeather>(api, CurrentWeatherBeanConverterImpl()), CurrentWeatherModule {

    private val forecastWeatherBeanConverter by lazy { ForecastWeatherBeanConverterImpl() }

    override fun getCurrentWeatherByCityID(options: Map<String, String>): Single<CurrentWeather> =
            api.getCurrentWeatherByCityID(options)
                    .onErrorResumeNext(NetworkErrorUtils.rxParseSingleError())
                    .compose(converter.singleINtoOUT())

    override fun getCurrentWeatherByGeoCoordinates(options: Map<String, String>): Single<CurrentWeather> =
            api.getCurrentWeatherByGeoCoordinates(options)
                    .onErrorResumeNext(NetworkErrorUtils.rxParseSingleError())
                    .compose(converter.singleINtoOUT())
}
