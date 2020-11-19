package com.marchuk.app.data.api

import com.marchuk.app.core.utils.network.NetworkResult
import com.marchuk.app.data.models.ForecastItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    /**
     *  @param[query] query in format of "Latitude,Longitude" as decimal values
     */
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("days") days: Int = 7
    ): NetworkResult<ForecastItemResponse>

}