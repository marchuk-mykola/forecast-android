package com.marchuk.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayForecastHolderResponse(
    @Json(name = "date")
    val date: String,
    @Json(name = "date_epoch")
    val dateEpoch: Int,
    @Json(name = "day")
    val day: DailyForecastResponse,
    @Json(name = "hour")
    val hourForecast: List<HourlyForecastResponse>
)