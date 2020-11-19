package com.marchuk.app.forecast.presentation.models

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class HourlyForecastRecyclerModel(
    val time: String,
    val tempC: Double
) : DelegateAdapterItem, Parcelable {

    val currentTimeFormatted: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val date = simpleDateFormat.parse(time)
            val dayDateFormat = SimpleDateFormat("h a", Locale.ENGLISH) // 12:08 PM
            return dayDateFormat.format(date).toLowerCase()
        }
}