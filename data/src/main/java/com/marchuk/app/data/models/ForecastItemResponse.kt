package com.marchuk.app.data.models

import com.marchuk.app.data.models.LocationResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastItemResponse(
    @Json(name = "location")
    val location: LocationResponse,
    @Json(name = "current")
    val currentForecast: CurrentForecastResponse,
    @Json(name = "forecast")
    val dailyForecastsHolder: DayForecastsHolderResponse
)