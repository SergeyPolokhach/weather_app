package com.polohach.weather.providers


object ProviderInjector {

    val currentWeather: CurrentWeatherProvider by lazy { CurrentWeatherProviderImpl() }
    val forecastWeather: ForecastWeatherProvider by lazy { ForecastWeatherProviderImpl() }
    val city: CityProvider by lazy { CityProviderImpl() }
}
