package com.polohach.weather.ui.screens.main.detail_weather.page

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import com.polohach.weather.R
import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.ui.base.BaseFragment
import com.polohach.weather.ui.screens.main.detail_weather.DetailWeatherVM
import kotlinx.android.synthetic.main.fragment_current_weather_page.*


class CurrentWeatherPage : BaseFragment<DetailWeatherVM>() {

    companion object {
        private const val CITY_ID_EXTRA = "CITY_ID_EXTRA"

        fun getBundle(cityId: Long?) = Bundle().apply {
            putLong(CITY_ID_EXTRA, cityId ?: 0)
        }
    }

    override val layoutId: Int = R.layout.fragment_current_weather_page
    override val viewModelClass = DetailWeatherVM::class.java
    private var cityId: Long? = null

    override fun getScreenTitle() = R.string.title_weather

    override fun getToolbarId() = R.id.toolbar

    override fun hasToolbar() = true

    override fun needToShowBackNav() = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getExtra()
    }

    override fun observeLiveData(viewModel: DetailWeatherVM) {
        viewModel.apply {
            getCurrentWeather(cityId)
            currentWeatherLD.observe(this@CurrentWeatherPage, Observer {
                initUi(it)
            })
            noLocalWeatherLD.observe(this@CurrentWeatherPage, Observer {
                initUi(null)
            })
        }
    }

    private fun initUi(currentWeather: CurrentWeather?) {
        currentWeather?.run {
            tvCityName.text = name ?: getString(R.string.empty_data_error)
            tvTemp.text = getString(R.string.temperature, main?.temp)
            tvPressure.text = getString(R.string.pressure, main?.pressure)
            tvHumidity.text = getString(R.string.humidity, main?.humidity)
            tvWindSpeed.text = getString(R.string.wind_speed, wind?.speed)
            tvDescription.text = getString(R.string.description, weather?.firstOrNull()?.description)
        }
    }

    private fun getExtra() {
        cityId = arguments?.getLong(CITY_ID_EXTRA)
    }
}
