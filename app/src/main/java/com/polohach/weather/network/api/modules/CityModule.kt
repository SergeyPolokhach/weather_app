package com.polohach.weather.network.api.modules

import com.polohach.weather.models.City
import com.polohach.weather.network.api.beans.CityBean
import com.polohach.weather.network.api.converters.CityBeanConverterImpl
import com.polohach.weather.network.api.retrofit.CityApi
import io.reactivex.Single


interface CityModule {

    fun getCities(): Single<List<City>>
}

class CityModuleImpl(api: CityApi) :
        BaseRxModule<CityApi, CityBean, City>(api, CityBeanConverterImpl()), CityModule {

    override fun getCities(): Single<List<City>> =
            api.getCities()
                    .onErrorResumeNext(NetworkErrorUtils.rxParseSingleError())
                    .compose(converter.listSingleINtoOUT())
}
