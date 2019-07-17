package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class WindBean(@JsonProperty("deg")
                    var deg: Double?,
                    @JsonProperty("speed")
                    var speed: Double?)
