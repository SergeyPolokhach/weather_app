package com.polohach.weather.network.exceptions

import com.cleveroad.bootstrap.kotlin_core.network.ApiException
import com.cleveroad.bootstrap.kotlin_core.network.ValidationError
import com.polohach.weather.R
import com.polohach.weather.extensions.getString

class ServerException(statusCode: Int? = null,
                      v: String? = null,
                      message: String? = null,
                      errors: List<ValidationError>? = null,
                      stacktrace: String? = null) : ApiException(statusCode, v, message, errors, stacktrace) {

    companion object {
        private val ERROR_MESSAGE = getString(R.string.server_error)
        private const val STATUS_CODE = 500
    }

    override val message: String = ERROR_MESSAGE
    override var statusCode: Int? = STATUS_CODE
}
