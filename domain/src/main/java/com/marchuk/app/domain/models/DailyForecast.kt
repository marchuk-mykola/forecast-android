package com.marchuk.app.domain.models

import android.os.Parcelable
import com.marchuk.app.domain.models.Condition
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyForecast(
    val avgHumidity: Double,
    val avgTempC: Double,
    val avgVisibilityKm: Double,
    val condition: Condition,
    val maxTempC: Double,
    val maxWindKph: Double,
    val minTempC: Double,
    val totalPrecipitationMm: Double
) : Parcelable