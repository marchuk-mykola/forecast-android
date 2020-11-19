package com.marchuk.app.core.utils.network

import com.marchuk.app.core.utils.network.baseError.ErrorResponse
import com.marchuk.app.core.utils.network.retrofit.ErrorCode

typealias NetworkResult<S> = Result<S, ErrorResponse>
typealias DomainResult<S> = Result<S, ErrorCode>

sealed class Result<out T : Any?, out U : Any> {

    /**
     * Success response with body
     */
    data class Success<T : Any?>(val body: T) : Result<T, Nothing>()

    sealed class Error<U : Any> : Result<Nothing, U>() {
        /**
         * Failure response with body
         */
        data class Api<U : Any>(val body: U) : Error<U>()

        /**
         * Network error
         */
        data class Domain(val exception: Exception) : Error<Nothing>()

        /**
         * For example, json parsing error
         */
        data class Unknown(val throwable: Throwable? = null) : Error<Nothing>()
    }

}