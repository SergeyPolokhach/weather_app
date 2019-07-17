package com.polohach.weather.providers.base

import com.polohach.weather.models.Model


abstract class BaseOnlineProvider<M : Model<*>, NetworkModule> : Provider<M> {

    val networkModule: NetworkModule = this.initNetworkModule()

    protected abstract fun initNetworkModule(): NetworkModule
}