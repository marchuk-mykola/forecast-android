package com.marchuk.app.forecast.presentation.models

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.Condition
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class DailyForecastRecyclerModel(
    val time: String,
    val maxTempC: Double,
    val minTempC: Double,
    val condition: Condition
) : DelegateAdapterItem, Parcelable {

    val currentDateFormatted: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = simpleDateFormat.parse(time)
            val dayDateFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
            return dayDateFormat.format(date)
        }

}