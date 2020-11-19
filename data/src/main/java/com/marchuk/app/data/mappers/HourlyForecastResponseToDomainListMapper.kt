package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.ListMapper
import com.marchuk.app.data.models.HourlyForecastResponse
import com.marchuk.app.domain.models.HourlyForecast

internal class HourlyForecastResponseToDomainListMapper(
    private val hourlyForecastResponseToDomainMapper: HourlyForecastResponseToDomainMapper
) : ListMapper<HourlyForecastResponse, HourlyForecast> {

    override fun invoke(input: List<HourlyForecastResponse>): List<HourlyForecast> =
        input.map { hourlyForecastResponseToDomainMapper(it) }

}