package com.polohach.weather.network.api.retrofit

import com.polohach.weather.network.V2_5
import com.polohach.weather.network.api.beans.CurrentWeatherBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface CurrentWeatherApi {

    @GET("$V2_5/weather")
    fun getCurrentWeatherByCityID(@QueryMap options: Map<String, String>): Single<CurrentWeatherBean>

    @GET("$V2_5/weather")
    fun getCurrentWeatherByGeoCoordinates(@QueryMap options: Map<String, String>): Single<CurrentWeatherBean>
}
