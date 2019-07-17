package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class MainBean(@JsonProperty("humidity")
                    var humidity: Int?,
                    @JsonProperty("pressure")
                    var pressure: Int?,
                    @JsonProperty("temp")
                    var temp: Double?,
                    @JsonProperty("temp_max")
                    var tempMax: Double?,
                    @JsonProperty("temp_min")
                    var tempMin: Double?)