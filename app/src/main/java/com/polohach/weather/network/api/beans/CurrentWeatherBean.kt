package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty


data class CurrentWeatherBean(@JsonProperty("id")
                              var id: Long?,
                              @JsonProperty("base")
                              var base: String?,
                              @JsonProperty("cod")
                              var cod: Int?,
                              @JsonProperty("coord")
                              var coord: CoordBean?,
                              @JsonProperty("dt")
                              var dt: Int?,
                              @JsonProperty("main")
                              var main: MainBean?,
                              @JsonProperty("name")
                              var name: String?,
                              @JsonProperty("timezone")
                              var timezone: Int?,
                              @JsonProperty("weather")
                              var weather: List<WeatherBean>?,
                              @JsonProperty("wind")
                              var wind: WindBean?)
