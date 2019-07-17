package com.polohach.weather.ui.screens.main.detail_weather.page


import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import com.cleveroad.bootstrap.kotlin_core.ui.NO_ID
import com.polohach.weather.R
import com.polohach.weather.models.DayWeather
import com.polohach.weather.ui.base.SimpleListFragment
import com.polohach.weather.ui.screens.main.detail_weather.DetailWeatherVM
import com.polohach.weather.ui.screens.main.detail_weather.ForecastWeatherAdapter


class ForecastWeatherPage : SimpleListFragment<DetailWeatherVM, DayWeather>() {

    companion object {
        private const val CITY_ID_EXTRA = "CITY_ID_EXTRA"

        fun getBundle(cityId: Long?) = Bundle().apply {
            putLong(CITY_ID_EXTRA, cityId ?: 0)
        }
    }

    override val layoutId: Int = R.layout.fragment_forecast_weather_page
    override val viewModelClass = DetailWeatherVM::class.java

    override val recyclerViewId = R.id.rvWeather
    override val noResultViewId = NO_ID
    override val refreshLayoutId = R.id.srWeather

    private var adapter: ForecastWeatherAdapter? = null
    private var cityId: Long? = null

    override fun getScreenTitle() = R.string.title_detail_weather

    override fun getToolbarId() = R.id.toolbar

    override fun hasToolbar() = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getExtra()
    }

    override fun observeLiveData(viewModel: DetailWeatherVM) {
        viewModel.apply {
            forecastWeatherLD.observe(this@ForecastWeatherPage, Observer { forecastWeather ->
                adapter?.apply {
                    city = forecastWeather.city
                    forecastWeather.daysWeather?.let { onInitialDataLoaded(it) }
                }
            })
            errorLD.observe(this@ForecastWeatherPage, Observer {
                hideLoadingProgress()
            })
            loadInitial()
        }
    }

    override fun getAdapter() = adapter
            ?: context?.let { ForecastWeatherAdapter(it) }
                    .apply { adapter = this }

    override fun loadInitial() = viewModel.getForecastWeather(cityId)

    override fun loadMoreData() = Unit

    private fun getExtra() {
        cityId = arguments?.getLong(CITY_ID_EXTRA)
    }
}
