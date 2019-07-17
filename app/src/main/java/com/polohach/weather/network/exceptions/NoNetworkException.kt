package com.polohach.weather.network.exceptions

import com.polohach.weather.R
import com.polohach.weather.extensions.getString

class NoNetworkException : Exception() {

    companion object {
        private val ERROR_MESSAGE = getString(R.string.no_internet_connection_error)
    }

    override val message: String = ERROR_MESSAGE
}
