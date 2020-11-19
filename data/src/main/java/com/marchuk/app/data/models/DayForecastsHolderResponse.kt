package com.marchuk.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayForecastsHolderResponse(
    @Json(name = "forecastday")
    val dailyForecasts: List<DayForecastHolderResponse>
)