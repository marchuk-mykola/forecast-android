package com.marchuk.app.core.utils.network.baseError

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiError(
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String
)