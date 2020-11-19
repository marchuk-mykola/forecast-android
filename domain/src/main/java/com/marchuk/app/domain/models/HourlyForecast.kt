package com.marchuk.app.domain.models

import android.os.Parcelable
import com.marchuk.app.domain.models.Condition
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HourlyForecast(
    val cloud: Int,
    val condition: Condition,
    val feelsLikeC: Double,
    val gustKph: Double,
    val heatIndexC: Double,
    val humidity: Int,
    val isDay: Boolean,
    val precipitationMm: Double,
    val pressureMb: Double,
    val tempC: Double,
    val time: String,
    val timeEpoch: Int,
    val visibilityKm: Double,
    val windKph: Double,
    val windChillC: Double
) : Parcelable