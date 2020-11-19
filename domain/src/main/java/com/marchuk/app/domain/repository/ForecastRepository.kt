package com.marchuk.app.domain.repository

import com.marchuk.app.core.utils.network.DomainResult
import com.marchuk.app.domain.models.ForecastItem

interface ForecastRepository {
    suspend fun getForecast(lat: Double, lon: Double): DomainResult<ForecastItem>
}