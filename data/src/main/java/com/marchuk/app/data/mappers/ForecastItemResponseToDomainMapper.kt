package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.ForecastItemResponse
import com.marchuk.app.domain.models.ForecastItem

internal class ForecastItemResponseToDomainMapper(
    private val locationResponseToDomainMapper: LocationResponseToDomainMapper,
    private val currentForecastResponseToDomainMapper: CurrentForecastResponseToDomainMapper,
    private val dayForecastHolderResponseToDomainListMapper: DayForecastHolderResponseToDomainListMapper
) : Mapper<ForecastItemResponse, ForecastItem> {

    override fun invoke(input: ForecastItemResponse): ForecastItem =
        ForecastItem(
            location = locationResponseToDomainMapper(input.location),
            currentForecast = currentForecastResponseToDomainMapper(input.currentForecast),
            dailyForecasts = dayForecastHolderResponseToDomainListMapper(input.dailyForecastsHolder.dailyForecasts)
        )

}