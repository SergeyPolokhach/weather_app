package com.polohach.weather.network.api.errors

import com.fasterxml.jackson.annotation.JsonProperty


data class ServerError(@JsonProperty("__v")
                       var v: String? = null,
                       @JsonProperty("code")
                       val code: Int? = null,
                       @JsonProperty("message")
                       val message: String? = null,
                       @JsonProperty("errors")
                       var errors: List<Error>? = null)
