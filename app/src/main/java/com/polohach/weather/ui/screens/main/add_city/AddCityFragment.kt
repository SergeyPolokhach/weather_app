package com.polohach.weather.ui.screens.main.add_city

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.cleveroad.bootstrap.kotlin_core.ui.NO_ID
import com.cleveroad.bootstrap.kotlin_core.ui.NO_TITLE
import com.cleveroad.bootstrap.kotlin_ext.hideKeyboard
import com.polohach.weather.R
import com.polohach.weather.models.City
import com.polohach.weather.ui.base.SimpleListFragment
import com.polohach.weather.ui.screens.main.add_city.auto_complete.AutoCompleteAdapter
import com.polohach.weather.ui.screens.main.add_city.auto_complete.AutoCompleteCallback
import kotlinx.android.synthetic.main.fragment_add_city.*
import org.jetbrains.anko.find


class AddCityFragment : SimpleListFragment<AddCityVM, City>(),
        AutoCompleteCallback {

    companion object {
        private const val AUTO_COMPLETE_THRESHOLD = 2

        fun newInstance() = AddCityFragment().apply { arguments = Bundle() }
    }

    override val layoutId: Int = R.layout.fragment_add_city
    override val viewModelClass = AddCityVM::class.java
    override val recyclerViewId = R.id.rvWeather
    override val noResultViewId = NO_ID
    override val refreshLayoutId = NO_ID

    private val adapter by lazy { AutoCompleteAdapter(this, R.layout.item_dropdown) }
    private var cityAdapter: CityAdapter? = null

    override fun getScreenTitle() = NO_TITLE

    override fun getToolbarId() = R.id.toolbar

    override fun hasToolbar() = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
            super.onViewCreated(view, savedInstanceState)
                    .also { initUi() }

    override fun observeLiveData(viewModel: AddCityVM) {
        viewModel.apply {
            getCities()
            loadInitial()
            filteredCitiesLD.observe(this@AddCityFragment, Observer {
                onFilteredCitiesArrived(it)
            })
            savedCitiesLD.observe(this@AddCityFragment, Observer {
                onInitialDataLoaded(it)
            })
            saveCityLD.observe(this@AddCityFragment, Observer {
                cityAdapter?.addItem(it)
            })
        }
    }

    override fun getAdapter() = cityAdapter
            ?: context?.let { CityAdapter(it) }
                    .apply { cityAdapter = this }

    override fun loadInitial() = viewModel.getMyCities()

    override fun loadMoreData() = Unit

    override fun onFilter(constraint: CharSequence) =
            viewModel.filterCity(constraint)

    override fun onDropdownViewClick(city: City) {
        hideKeyboard()
        actvSearch.apply {
            city.name?.let {
                setText(it)
                setSelection(it.length)
            }
            viewModel.saveCity(city)
            dismissDropDown()
        }
    }

    override fun onDropdownViewInflated(view: View, city: City) {
        view.find<TextView>(R.id.tvDropdown).text = city.name
    }

    private fun initUi() {
        actvSearch.run {
            setAdapter(this@AddCityFragment.adapter)
            threshold = AUTO_COMPLETE_THRESHOLD
        }
    }

    private fun onFilteredCitiesArrived(cities: List<City>) {
        adapter.addAllNotify(cities)
    }
}
