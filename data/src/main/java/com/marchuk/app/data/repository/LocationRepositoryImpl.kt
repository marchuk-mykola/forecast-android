package com.marchuk.app.data.repository

import com.marchuk.app.core.database.SearchedLocationDao
import com.marchuk.app.core.utils.network.NetworkResult
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.data.api.AutoCompleteApi
import com.marchuk.app.data.mappers.LocationDomainToEntityMapper
import com.marchuk.app.data.mappers.LocationEntityToDomainListMapper
import com.marchuk.app.data.mappers.LocationResponseToDomainListMapper
import com.marchuk.app.domain.models.Location
import com.marchuk.app.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val api: AutoCompleteApi,
    private val dao: SearchedLocationDao,
    private val domainToEntityMapper: LocationDomainToEntityMapper,
    private val locationEntityToDomainListMapper: LocationEntityToDomainListMapper,
    private val listLocationResponseToDomainMapper: LocationResponseToDomainListMapper
) : LocationRepository {

    override suspend fun deleteLocation(lat: Double, lon: Double) {
        dao.delete(lat = lat, lon = lon)
    }

    override suspend fun insertLocation(location: Location) {
        dao.insert(domainToEntityMapper(location))
    }

    override fun getLastSearchedLocations(): Flow<List<Location>> {
        return dao.getCities().map {
            locationEntityToDomainListMapper(it)
        }
    }

    override suspend fun searchLocationsByName(cityName: String): NetworkResult<List<Location>> {
        return when (val response = api.getLocationItems(cityName)) {
            is Result.Success -> Result.Success(
                listLocationResponseToDomainMapper(response.body)
            )
            is Result.Error.Api -> response
            is Result.Error.Domain -> response
            is Result.Error.Unknown -> response
        }
    }

}