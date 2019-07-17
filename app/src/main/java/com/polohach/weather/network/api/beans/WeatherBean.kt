package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherBean(@JsonProperty("id")
                       var id: Long?,
                       @JsonProperty("description")
                       var description: String?,
                       @JsonProperty("icon")
                       var icon: String?,
                       @JsonProperty("main")
                       var main: String?)
