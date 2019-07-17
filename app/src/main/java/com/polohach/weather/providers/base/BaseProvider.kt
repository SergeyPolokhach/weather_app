package com.polohach.weather.providers.base

import com.polohach.weather.models.Model
import com.polohach.weather.repositories.Repository


abstract class BaseProvider<M : Model<*>, NetworkModule, Repo : Repository<M>>
    : BaseOnlineProvider<M, NetworkModule>() {

    val repository: Repo = this.initRepository()

    protected abstract fun initRepository(): Repo
}
