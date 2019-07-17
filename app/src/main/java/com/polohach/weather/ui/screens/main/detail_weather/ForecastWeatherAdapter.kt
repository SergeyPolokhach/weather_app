package com.polohach.weather.ui.screens.main.detail_weather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cleveroad.bootstrap.kotlin_core.ui.adapter.BaseRecyclerViewAdapter
import com.polohach.weather.R
import com.polohach.weather.enums.ItemViewType
import com.polohach.weather.models.City
import com.polohach.weather.models.DayWeather
import kotlinx.android.synthetic.main.item_current_weather.view.*
import kotlinx.android.synthetic.main.item_header_current_weather.view.*

class ForecastWeatherAdapter(context: Context) : BaseRecyclerViewAdapter<DayWeather, RecyclerView.ViewHolder>(context) {

    var city: City? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                ItemViewType.HEADER() -> ForecastWeatherHeaderViewHolder.newInstance(inflater, parent)
                else -> ForecastWeatherViewHolder.newInstance(inflater, parent)
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            ItemViewType.HEADER() -> {
                (holder as? ForecastWeatherHeaderViewHolder)?.bind(city)
            }
            else -> {
                (holder as? ForecastWeatherViewHolder)?.bind(data[position - 1])
            }
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = ItemViewType.from(position)()

    class ForecastWeatherHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
                    ForecastWeatherHeaderViewHolder(inflater.inflate(R.layout.item_header_current_weather, parent, false))
        }

        fun bind(data: City?) {
            itemView.tvCityName.text = data?.name
        }
    }

    class ForecastWeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
                    ForecastWeatherViewHolder(inflater.inflate(R.layout.item_current_weather, parent, false))
        }

        fun bind(data: DayWeather) {
            data.run {
                val context = itemView.context
                itemView.tvDate.text = date
                itemView.tvTemp.text = context.getString(R.string.temperature, main?.temp)
                itemView.tvPressure.text = context.getString(R.string.pressure, main?.pressure)
                itemView.tvHumidity.text = context.getString(R.string.humidity, main?.humidity)
                itemView.tvWindSpeed.text = context.getString(R.string.wind_speed, wind?.speed)
                itemView.tvDescription.text = context.getString(R.string.description, weather?.firstOrNull()?.description)
            }
        }
    }
}
