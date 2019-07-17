package com.polohach.weather.ui.screens.main.add_city.auto_complete

import android.view.View
import com.polohach.weather.models.City

interface AutoCompleteCallback {
    fun onFilter(constraint: CharSequence)

    fun onDropdownViewClick(city: City)

    fun onDropdownViewInflated(view: View, city: City)
}