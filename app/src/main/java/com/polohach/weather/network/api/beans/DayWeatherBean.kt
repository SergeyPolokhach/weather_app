package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty


data class DayWeatherBean(@JsonProperty("main")
                          var main: MainBean?,
                          @JsonProperty("weather")
                          var weather: List<WeatherBean>?,
                          @JsonProperty("wind")
                          var wind: WindBean?,
                          @JsonProperty("dt_txt")
                          var date: String?)
