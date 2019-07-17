package com.polohach.weather.network.api.beans

import com.fasterxml.jackson.annotation.JsonProperty


data class CityBean(@JsonProperty("id")
                    var id: Long?,
                    @JsonProperty("name")
                    var name: String?)
