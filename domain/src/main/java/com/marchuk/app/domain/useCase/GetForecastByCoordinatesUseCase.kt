package com.marchuk.app.domain.useCase

import com.marchuk.app.core.utils.network.DomainResult
import com.marchuk.app.domain.models.ForecastItem
import com.marchuk.app.domain.repository.ForecastRepository

class GetForecastByCoordinatesUseCase(
    private val forecastRepository: ForecastRepository
) {

    suspend operator fun invoke(lat: Double, lon: Double): DomainResult<ForecastItem> {
        return forecastRepository.getForecast(lat = lat, lon = lon)
    }

}