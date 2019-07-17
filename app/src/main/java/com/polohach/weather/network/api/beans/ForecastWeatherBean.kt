package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty


data class ForecastWeatherBean(@JsonProperty("city")
                               var city: CityBean?,
                               @JsonProperty("list")
                               var weathers: List<DayWeatherBean>?)
