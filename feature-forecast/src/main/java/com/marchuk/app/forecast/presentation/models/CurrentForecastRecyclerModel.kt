package com.marchuk.app.forecast.presentation.models

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.Condition
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class CurrentForecastRecyclerModel(
    val condition: Condition,
    val feelsLikeC: Double,
    val isDay: Boolean,
    val lastUpdated: String,
    val tempC: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val cityName: String
) : DelegateAdapterItem, Parcelable {

    val currentDateFormatted: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            val date = simpleDateFormat.parse(lastUpdated)
            val dayDateFormat = SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH)
            return dayDateFormat.format(date)
        }

}

