package com.marchuk.app.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentForecast(
    val cloud: Int,
    val condition: Condition,
    val feelsLikeC: Double,
    val gustKph: Double,
    val humidity: Int,
    val isDay: Boolean,
    val lastUpdated: String,
    val lastUpdatedEpoch: Int,
    val precipitationMm: Double,
    val pressureMb: Double,
    val tempC: Double,
    val windKph: Double
) : Parcelable