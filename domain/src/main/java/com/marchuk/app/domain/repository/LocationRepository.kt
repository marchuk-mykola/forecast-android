package com.marchuk.app.domain.repository

import com.marchuk.app.core.utils.network.NetworkResult
import com.marchuk.app.domain.models.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun insertLocation(location: Location)
    fun getLastSearchedLocations(): Flow<List<Location>>

    suspend fun searchLocationsByName(cityName: String): NetworkResult<List<Location>>
    suspend fun deleteLocation(lat: Double, lon: Double)
}