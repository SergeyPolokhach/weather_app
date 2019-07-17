package com.polohach.weather.network.clients

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.polohach.weather.App
import com.polohach.weather.BuildConfig
import com.polohach.weather.network.MockInterceptor
import com.polohach.weather.network.TIMEOUT_IN_SECONDS
import com.polohach.weather.network.api.modules.*
import com.polohach.weather.network.api.retrofit.CityApi
import com.polohach.weather.network.api.retrofit.CurrentWeatherApi
import com.polohach.weather.network.api.retrofit.ForecastWeatherApi
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ServerClient : BaseServerClient() {

    val currentWeather: CurrentWeatherModule by lazy { CurrentWeatherModuleImpl(retrofit.create(CurrentWeatherApi::class.java)) }
    val forecastWeather: ForecastWeatherModule by lazy { ForecastWeatherModuleImpl(retrofit.create(ForecastWeatherApi::class.java)) }
    val city: CityModule by lazy { CityModuleImpl(retrofit.create(CityApi::class.java)) }

    override fun createApiEndpoint() = BuildConfig.ENDPOINT

    override fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .apply { addInterceptor(MockInterceptor(App.instance)) }
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(log())
                    addInterceptor(OkHttpProfilerInterceptor())
                }
            }
            .build()
}
