package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty


data class TemperatureBean(@JsonProperty("day")
                           var day: Double?,
                           @JsonProperty("eve")
                           var eve: Double?,
                           @JsonProperty("max")
                           var max: Double?,
                           @JsonProperty("min")
                           var min: Double?,
                           @JsonProperty("morn")
                           var morn: Double?,
                           @JsonProperty("night")
                           var night: Double?)
