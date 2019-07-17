package com.polohach.weather.enums


enum class OpenWeatherMapKeys(private val value: String) {
    UNITS("units"),
    QUERY("q"),
    CITY_ID("id"),
    LATITUDE("lat"),
    LONGITUDE("lon"),
    COUNT("cnt"),
    ZIP_CODE("zip");

    operator fun invoke() = value
}
