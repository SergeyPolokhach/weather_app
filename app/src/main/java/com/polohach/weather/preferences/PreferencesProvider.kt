package com.polohach.weather.preferences

internal object PreferencesProvider {

    var token by StringPD()

    var refreshToken by StringPD()

    fun saveSession(token: String?, refreshToken: String?) {
        PreferencesProvider.token = token
        PreferencesProvider.refreshToken = refreshToken
    }

    fun clearSession() {
        token = null
        refreshToken = null
    }

}