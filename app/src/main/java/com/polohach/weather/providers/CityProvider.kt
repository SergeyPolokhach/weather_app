package com.polohach.weather.providers

import com.polohach.weather.models.City
import com.polohach.weather.network.NetworkModule
import com.polohach.weather.network.api.modules.CityModule
import com.polohach.weather.providers.base.BaseProvider
import com.polohach.weather.repositories.CityRepository
import com.polohach.weather.repositories.CityRepositoryImpl
import io.reactivex.Single


interface CityProvider {

    fun saveCity(city: City): Single<City>

    fun getLocaleCities(): Single<List<City>>

    fun getCities(): Single<List<City>>
}

class CityProviderImpl : BaseProvider<City, CityModule, CityRepository>(), CityProvider {

    override fun initNetworkModule() = NetworkModule.client.city

    override fun initRepository(): CityRepository = CityRepositoryImpl()

    override fun saveCity(city: City): Single<City> =
            repository.saveCity(city)

    override fun getLocaleCities(): Single<List<City>> =
            repository.getCities()

    override fun getCities(): Single<List<City>> =
            networkModule.getCities()
}
