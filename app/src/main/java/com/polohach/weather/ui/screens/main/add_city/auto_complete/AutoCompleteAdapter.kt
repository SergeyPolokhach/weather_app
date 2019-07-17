package com.polohach.weather.ui.screens.main.add_city.auto_complete

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.annotation.LayoutRes
import com.polohach.weather.models.City
import java.lang.ref.WeakReference


class AutoCompleteAdapter(callback: AutoCompleteCallback,
                          @LayoutRes private val layoutRes: Int) : BaseAutoCompleteAdapter<City>() {

    private val callbackRef = WeakReference<AutoCompleteCallback>(callback)

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            LayoutInflater.from(parent?.context)
                    ?.inflate(layoutRes, parent, false)?.apply {
                        setOnClickListener {
                            callbackRef.get()?.onDropdownViewClick(data[position])
                        }
                        callbackRef.get()?.onDropdownViewInflated(this, data[position])
                    }

    override fun getFilter() = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            constraint?.let { callbackRef.get()?.onFilter(it) }
            return FilterResults()
                    .also { it.count = 0 }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) = Unit
    }
}