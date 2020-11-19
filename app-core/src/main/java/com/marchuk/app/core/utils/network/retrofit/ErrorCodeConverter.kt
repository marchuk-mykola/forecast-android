package com.marchuk.app.core.utils.network.retrofit

import com.marchuk.app.core.utils.network.baseError.ApiError

enum class ErrorCode(val statusCode: Int, val code: Int, val description: String) {
    MISSING_API_KEY(401, 1002, "API key not provided"),
    MISSING_PARAMETER_Q(400, 1003, "Parameter 'q' not provided"),
    INVALID_API_URL(400, 1005, "API request url is invalid"),
    NO_LOCATION_FOUND(400, 1006, "No location found matching parameter 'q'"),
    INVALID_API_KEY(401, 2006, "API key provided is invalid"),
    EXCEEDED_QUOTA(403, 2007, "API key has exceeded calls per month quota"),
    DISABLED_API_KEY(403, 2008, "API key has been disabled"),
    INTERNAL_API_ERROR(400, 9999, "Internal application error"),
    UNKNOWN_ERROR(-1, -1, "Unknown error");

    companion object {
        fun fromError(error: ApiError): ErrorCode {
            return values().single { errorCode -> errorCode.code == error.code } ?: UNKNOWN_ERROR
        }
    }

}