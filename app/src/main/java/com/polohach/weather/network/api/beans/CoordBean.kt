package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty

data class CoordBean(@JsonProperty("lat")
                     var lat: Double?,
                     @JsonProperty("lon")
                     var lon: Double?)
