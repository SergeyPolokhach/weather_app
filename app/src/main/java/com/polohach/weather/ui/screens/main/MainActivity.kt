package com.polohach.weather.ui.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.polohach.weather.R
import com.polohach.weather.ui.base.BaseActivity
import com.polohach.weather.ui.screens.main.add_city.AddCityFragment
import com.polohach.weather.ui.screens.main.detail_weather.DetailWeatherFragment
import com.polohach.weather.ui.screens.main.weather.WeatherCallback
import com.polohach.weather.ui.screens.main.weather.WeatherFragment

class MainActivity : BaseActivity<MainVM>(), WeatherCallback {

    companion object {
        fun start(context: Context?) {
            context?.apply ctx@{ startActivity(getIntent(this@ctx)) }
        }

        fun getIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }

    override val viewModelClass = MainVM::class.java

    override val containerId = R.id.flContainer

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) replaceFragment(WeatherFragment.newInstance(), false)
    }

    override fun observeLiveData(viewModel: MainVM) = Unit

    override fun openAddCity() {
        replaceFragment(AddCityFragment.newInstance())
    }

    override fun openDetailWeather(cityId: Long) {
        replaceFragment(DetailWeatherFragment.newInstance(cityId))
    }
}
