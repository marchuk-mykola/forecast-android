package com.marchuk.app.domain.models

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastItem(
    val location: Location,
    val currentForecast: CurrentForecast,
    val dailyForecasts: List<DayForecastHolder>
) : Parcelable, DelegateAdapterItem