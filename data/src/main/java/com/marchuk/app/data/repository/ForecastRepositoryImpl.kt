package com.marchuk.app.data.repository

import com.marchuk.app.core.database.SearchedLocationDao
import com.marchuk.app.core.utils.network.DomainResult
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.core.utils.network.retrofit.ErrorCode
import com.marchuk.app.data.api.ForecastApi
import com.marchuk.app.data.mappers.ForecastItemResponseToDomainMapper
import com.marchuk.app.data.mappers.LocationResponseToEntityMapper
import com.marchuk.app.data.models.LocationResponse
import com.marchuk.app.domain.models.ForecastItem
import com.marchuk.app.domain.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ForecastRepositoryImpl(
    private val api: ForecastApi,
    private val dao: SearchedLocationDao,
    private val locationResponseToEntityMapper: LocationResponseToEntityMapper,
    private val forecastItemResponseToDomainMapper: ForecastItemResponseToDomainMapper
) : ForecastRepository {

    override suspend fun getForecast(lat: Double, lon: Double): DomainResult<ForecastItem> {
        return when (val response = api.getForecast("$lat,$lon")) {
            is Result.Success -> {
                saveLocation(response.body.location)
                Result.Success(forecastItemResponseToDomainMapper(response.body))
            }
            is Result.Error.Api -> Result.Error.Api(ErrorCode.fromError(response.body.error))
            is Result.Error.Domain -> response
            is Result.Error.Unknown -> response
        }
    }

    private suspend fun saveLocation(location: LocationResponse) {
        withContext(Dispatchers.IO) {
            dao.insert(locationEntity = locationResponseToEntityMapper(location))
//            dao.insertAndDeleteExcess(locationResponseToEntityMapper(location))
        }
    }

}