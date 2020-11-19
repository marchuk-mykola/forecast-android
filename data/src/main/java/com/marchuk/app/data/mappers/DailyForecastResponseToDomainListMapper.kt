package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.ListMapper
import com.marchuk.app.data.models.DailyForecastResponse
import com.marchuk.app.domain.models.DailyForecast

internal class DailyForecastResponseToDomainListMapper(
    private val dailyForecastResponseToDomainMapper: DailyForecastResponseToDomainMapper
) : ListMapper<DailyForecastResponse, DailyForecast> {

    override fun invoke(input: List<DailyForecastResponse>): List<DailyForecast> =
        input.map { dailyForecastResponseToDomainMapper(it) }

}