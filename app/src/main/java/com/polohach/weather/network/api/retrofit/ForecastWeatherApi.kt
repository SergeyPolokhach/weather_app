package com.polohach.weather.network.api.retrofit

import com.polohach.weather.network.V2_5
import com.polohach.weather.network.api.beans.ForecastWeatherBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ForecastWeatherApi {

    @GET("$V2_5/forecast")
    fun getForecastWeatherByCityID(@QueryMap options: Map<String, String>): Single<ForecastWeatherBean>
}
