package com.marchuk.app.data.api

import com.marchuk.app.core.utils.network.NetworkResult
import com.marchuk.app.data.models.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompleteApi {

    @GET("search.json")
    suspend fun getLocationItems(@Query("q") cityName: String): NetworkResult<List<LocationResponse>>

}