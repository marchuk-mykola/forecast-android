package com.marchuk.app.core.utils.network.baseError

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "error")
    val error: ApiError
)