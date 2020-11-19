package com.marchuk.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentForecastResponse(
    @Json(name = "cloud")
    val cloud: Int,
    @Json(name = "condition")
    val condition: ConditionResponse,
    @Json(name = "feelslike_c")
    val feelsLikeC: Double,
    @Json(name = "gust_kph")
    val gustKph: Double,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "is_day")
    val isDay: Int,
    @Json(name = "last_updated")
    val lastUpdated: String,
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @Json(name = "precip_mm")
    val precipMm: Double,
    @Json(name = "pressure_mb")
    val pressureMb: Double,
    @Json(name = "temp_c")
    val tempC: Double,
    @Json(name = "vis_km")
    val visibilityKm: Double,
    @Json(name = "wind_kph")
    val windKph: Double
)