package com.polohach.weather.providers

import com.polohach.weather.models.ForecastWeather
import com.polohach.weather.network.NetworkModule
import com.polohach.weather.network.api.modules.ForecastWeatherModule
import com.polohach.weather.providers.base.BaseOnlineProvider
import io.reactivex.Single


interface ForecastWeatherProvider {

    fun getForecastWeatherByCityID(options: Map<String, String>): Single<ForecastWeather>
}

class ForecastWeatherProviderImpl : BaseOnlineProvider<ForecastWeather, ForecastWeatherModule>(), ForecastWeatherProvider {

    override fun initNetworkModule() = NetworkModule.client.forecastWeather

    override fun getForecastWeatherByCityID(options: Map<String, String>): Single<ForecastWeather> =
            networkModule.getForecastWeatherByCityID(options)
}
