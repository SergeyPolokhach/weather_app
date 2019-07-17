package com.polohach.weather.ui.screens.main.weather

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import com.cleveroad.bootstrap.kotlin_core.ui.NO_ID
import com.cleveroad.bootstrap.kotlin_core.utils.misc.bindInterfaceOrThrow
import com.cleveroad.bootstrap.kotlin_ext.setClickListeners
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.polohach.weather.R
import com.polohach.weather.extensions.dp2px
import com.polohach.weather.models.City
import com.polohach.weather.models.CurrentWeather
import com.polohach.weather.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherFragment : BaseFragment<WeatherVM>(), View.OnClickListener {

    companion object {
        private const val DROP_DOWN_VERTICAL_OFFSET = 48F

        fun newInstance() = WeatherFragment().apply { arguments = Bundle() }
    }

    override val layoutId: Int = R.layout.fragment_weather
    override val viewModelClass = WeatherVM::class.java

    private var callback: WeatherCallback? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val spinnerSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.run {
                citiesLD.value?.get(position)?.id?.let {
                    if (it != NO_ID.toLong()) getCurrentWeather(it) else gelLastLocation()
                }
            }
        }
    }

    override fun getScreenTitle() = R.string.title_weather

    override fun getToolbarId() = R.id.toolbar

    override fun hasToolbar() = true

    override fun needToShowBackNav() = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = bindInterfaceOrThrow<WeatherCallback>(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
            super.onViewCreated(view, savedInstanceState)
                    .also { setClickListeners(fabAddCity, llDataContainer) }

    override fun observeLiveData(viewModel: WeatherVM) {
        viewModel.apply {
            citiesLD.observe(this@WeatherFragment, Observer {
                initSpinner(spCity, it)
            })
            currentWeatherLD.observe(this@WeatherFragment, Observer {
                initUi(it)
            })
            noLocalWeatherLD.observe(this@WeatherFragment, Observer {
                initUi(null)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMyCities()
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fabAddCity -> callback?.openAddCity()
            R.id.llDataContainer -> viewModel.currentWeatherLD.value
                    ?.id?.let { callback?.openDetailWeather(it) }
        }
    }

    private fun initUi(currentWeather: CurrentWeather?) {
        tvTemp.text = currentWeather?.main?.temp?.let { getString(R.string.temperature, it) } ?: ""
        tvCityName.text = currentWeather?.name ?: getString(R.string.empty_data_error)
    }

    private fun initSpinner(spinner: Spinner, cities: List<City>) {
        context?.let {
            ArrayAdapter(it, R.layout.item_city, R.id.tvCity, cities)
                    .also { arrayAdapter ->
                        arrayAdapter.setDropDownViewResource(R.layout.item_dropdown_city)
                        spinner.apply {
                            adapter = arrayAdapter
                            dropDownVerticalOffset = dp2px(DROP_DOWN_VERTICAL_OFFSET)
                            onItemSelectedListener = spinnerSelectedListener
                        }
                    }
        }
    }

    private fun gelLastLocation() {
        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                isGrantedCallback = { subscribe() })
    }

    @SuppressLint("MissingPermission")
    private fun subscribe() {
        fusedLocationClient
                ?.lastLocation
                ?.addOnSuccessListener { location: Location? ->
                    location?.run {
                        viewModel.getOnlineCurrentWeatherByGeoCoordinates(LatLng(latitude, longitude))
                    }
                }
    }
}
