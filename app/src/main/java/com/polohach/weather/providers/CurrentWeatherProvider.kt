package com.polohach.weather.providers

import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.network.NetworkModule
import com.polohach.weather.network.api.modules.CurrentWeatherModule
import com.polohach.weather.providers.base.BaseProvider
import com.polohach.weather.repositories.CurrentWeatherRepository
import com.polohach.weather.repositories.CurrentWeatherRepositoryImpl
import io.reactivex.Single


interface CurrentWeatherProvider {

    fun getCurrentWeatherByCityID(options: Map<String, String>): Single<CurrentWeather>

    fun getLocalCurrentWeatherByCityID(cityId: Long): Single<CurrentWeather>

    fun getCurrentWeatherByGeoCoordinates(options: Map<String, String>): Single<CurrentWeather>
}

class CurrentWeatherProviderImpl : BaseProvider<CurrentWeather, CurrentWeatherModule, CurrentWeatherRepository>(), CurrentWeatherProvider {

    override fun initNetworkModule() = NetworkModule.client.currentWeather

    override fun initRepository(): CurrentWeatherRepository = CurrentWeatherRepositoryImpl()

    override fun getCurrentWeatherByCityID(options: Map<String, String>): Single<CurrentWeather> =
            networkModule.getCurrentWeatherByCityID(options)
                    .flatMap { repository.saveCurrentWeather(it) }

    override fun getLocalCurrentWeatherByCityID(cityId: Long): Single<CurrentWeather> =
            repository.getCurrentWeatherByCityID(cityId)

    override fun getCurrentWeatherByGeoCoordinates(options: Map<String, String>): Single<CurrentWeather> =
            networkModule.getCurrentWeatherByGeoCoordinates(options)
                    .flatMap { repository.saveCurrentWeather(it) }
}
