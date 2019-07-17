package com.polohach.weather.network.api.retrofit

import com.polohach.weather.network.api.beans.CityBean
import io.reactivex.Single
import retrofit2.http.GET


interface CityApi {
    @GET("cities")
    fun getCities(): Single<List<CityBean>>
}
