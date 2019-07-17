package com.polohach.weather.ui.screens.main.add_city.auto_complete

import android.widget.BaseAdapter
import android.widget.Filterable

abstract class BaseAutoCompleteAdapter<T> : BaseAdapter(), Filterable {

    protected val data = mutableListOf<T>()

    @Throws(ArrayIndexOutOfBoundsException::class)
    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = data.size

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun addAll(items: List<T>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun addAllNotify(items: List<T>) = with(data) {
        clear()
        addAll(items)
        notifyDataSetChanged()
    }
}