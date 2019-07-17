package com.polohach.weather.ui.screens.main.detail_weather

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cleveroad.bootstrap.kotlin_core.ui.view_pager_adapter.BaseFragmentStatePagerAdapter
import com.cleveroad.bootstrap.kotlin_core.ui.view_pager_adapter.FragmentInfoContainer
import com.polohach.weather.R
import com.polohach.weather.ui.base.BaseFragment
import com.polohach.weather.ui.screens.main.detail_weather.page.CurrentWeatherPage
import com.polohach.weather.ui.screens.main.detail_weather.page.ForecastWeatherPage
import kotlinx.android.synthetic.main.fragment_detail_weather.*


class DetailWeatherFragment : BaseFragment<DetailWeatherVM>() {

    companion object {
        private const val CITY_ID_EXTRA = "CITY_ID_EXTRA"

        fun newInstance(cityId: Long) = DetailWeatherFragment().apply {
            arguments = Bundle().apply {
                putLong(CITY_ID_EXTRA, cityId)
            }
        }
    }

    override val layoutId: Int = R.layout.fragment_detail_weather
    override val viewModelClass = DetailWeatherVM::class.java

    private var cityId: Long? = null
    private lateinit var viewPagerAdapter: BaseFragmentStatePagerAdapter

    override fun getScreenTitle() = R.string.title_detail_weather

    override fun getToolbarId() = R.id.toolbar

    override fun hasToolbar() = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getExtra()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
            super.onViewCreated(view, savedInstanceState)
                    .also { initUi(view.context) }

    override fun observeLiveData(viewModel: DetailWeatherVM) = Unit

    private fun initUi(ctx: Context) {
        val fragmentInfoContainers = mutableListOf<FragmentInfoContainer>()
                .apply {
                    add(FragmentInfoContainer(CurrentWeatherPage::class.java,
                            getString(R.string.today),
                            CurrentWeatherPage.getBundle(cityId)))
                    add(FragmentInfoContainer(ForecastWeatherPage::class.java,
                            getString(R.string.five_days),
                            ForecastWeatherPage.getBundle(cityId)))
                }
        viewPagerAdapter = BaseFragmentStatePagerAdapter(ctx, childFragmentManager, fragmentInfoContainers)
        vpDetailWeather.adapter = viewPagerAdapter
        tabs.setupWithViewPager(vpDetailWeather)
    }

    private fun getExtra() {
        cityId = arguments?.getLong(CITY_ID_EXTRA)
    }
}
