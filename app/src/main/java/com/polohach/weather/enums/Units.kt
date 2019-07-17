package com.polohach.weather.enums


enum class Units(private val value: String) {
    FAHRENHEIT("imperial"),
    CELSIUS("metric");

    operator fun invoke() = value
}