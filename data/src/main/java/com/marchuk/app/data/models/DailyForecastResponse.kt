package com.marchuk.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecastResponse(
    @Json(name = "avghumidity")
    val avgHumidity: Double,
    @Json(name = "avgtemp_c")
    val avgTempC: Double,
    @Json(name = "avgvis_km")
    val avgVisibilityKm: Double,
    @Json(name = "condition")
    val condition: ConditionResponse,
    @Json(name = "maxtemp_c")
    val maxTempC: Double,
    @Json(name = "maxwind_kph")
    val maxWindKph: Double,
    @Json(name = "mintemp_c")
    val minTempC: Double,
    @Json(name = "totalprecip_mm")
    val totalPrecipitationMm: Double
)