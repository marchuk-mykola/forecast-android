package com.marchuk.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionResponse(
    @Json(name = "icon")
    val icon: String,
    @Json(name = "text")
    val text: String
)