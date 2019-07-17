package com.polohach.weather.network.clients

import com.fasterxml.jackson.databind.ObjectMapper
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.polohach.weather.BuildConfig
import com.polohach.weather.network.NetworkModule
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

abstract class BaseServerClient {

    protected abstract fun createApiEndpoint(): String

    protected abstract fun createHttpClient(): OkHttpClient

    protected open var mapper: ObjectMapper = NetworkModule.mapper

    protected open val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .baseUrl(createApiEndpoint())
                .client(createHttpClient())
                .build()
    }

    protected open fun log(): LoggingInterceptor = LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("Request>>>>")
            .response("Response<<<<")
            .build()

}