package com.marchuk.app.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DayForecastHolder(
    val date: String,
    val dateEpoch: Int,
    val day: DailyForecast,
    val hourlyForecastsList: List<HourlyForecast>
) : Parcelable