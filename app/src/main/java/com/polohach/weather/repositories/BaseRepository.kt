package com.polohach.weather.repositories

import com.polohach.weather.models.Model


abstract class BaseRepository<M : Model<T>, DBModel, T> : Repository<M>
