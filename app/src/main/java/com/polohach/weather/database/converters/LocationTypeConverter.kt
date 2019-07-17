package com.polohach.weather.database.converters

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng


class LocationTypeConverter {

    companion object {
        private const val LAT_LNG_STRING = "%f,%f"
        private const val STRING_DELIMITER = ","
    }

    @TypeConverter
    fun fromLatLng(latLng: LatLng?): String? =
            latLng?.let {
                String.format(LAT_LNG_STRING, it.latitude, it.longitude)
            }

    @TypeConverter
    fun toLatLng(latLngString: String?): LatLng? =
            latLngString?.let { latLng ->
                latLng.split(STRING_DELIMITER.toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                        .let { arrays ->
                            LatLng(arrays.first().toDouble(), arrays.last().toDouble())
                        }
            }
}
