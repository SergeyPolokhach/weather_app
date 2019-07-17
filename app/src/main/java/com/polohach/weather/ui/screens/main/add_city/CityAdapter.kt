package com.polohach.weather.ui.screens.main.add_city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cleveroad.bootstrap.kotlin_core.ui.adapter.BaseRecyclerViewAdapter
import com.polohach.weather.R
import com.polohach.weather.models.City
import kotlinx.android.synthetic.main.item_header_current_weather.view.*

class CityAdapter(context: Context) : BaseRecyclerViewAdapter<City, CityAdapter.CityViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
            CityViewHolder.newInstance(inflater, parent)

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
            holder.bind(data[position])

    override fun getItemCount() = data.size

    fun addItem(city: City) {
        add(city)
        notifyDataSetChanged()
    }

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
                    CityViewHolder(inflater.inflate(R.layout.item_header_current_weather, parent, false))
        }

        fun bind(data: City?) {
            itemView.tvCityName.text = data?.name
        }
    }
}
