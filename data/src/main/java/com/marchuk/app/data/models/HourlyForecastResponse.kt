package com.marchuk.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyForecastResponse(
    @Json(name = "cloud")
    val cloud: Int,
    @Json(name = "condition")
    val condition: ConditionResponse,
    @Json(name = "feelslike_c")
    val feelsLikeC: Double,
    @Json(name = "gust_kph")
    val gustKph: Double,
    @Json(name = "heatindex_c")
    val heatIndexC: Double,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "is_day")
    val isDay: Int,
    @Json(name = "precip_mm")
    val precipitationMm: Double,
    @Json(name = "pressure_mb")
    val pressureMb: Double,
    @Json(name = "temp_c")
    val tempC: Double,
    @Json(name = "time")
    val time: String,
    @Json(name = "time_epoch")
    val timeEpoch: Int,
    @Json(name = "vis_km")
    val visibilityKm: Double,
    @Json(name = "wind_kph")
    val windKph: Double,
    @Json(name = "windchill_c")
    val windChillC: Double
)