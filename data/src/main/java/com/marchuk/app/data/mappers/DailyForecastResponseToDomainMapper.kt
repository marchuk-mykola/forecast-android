package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.DailyForecastResponse
import com.marchuk.app.domain.models.DailyForecast

internal class DailyForecastResponseToDomainMapper(
    private val conditionResponseToDomainMapper: ConditionResponseToDomainMapper
) : Mapper<DailyForecastResponse, DailyForecast> {

    override fun invoke(input: DailyForecastResponse): DailyForecast =
        DailyForecast(
            avgHumidity = input.avgHumidity,
            avgTempC = input.avgTempC,
            avgVisibilityKm = input.avgVisibilityKm,
            condition = conditionResponseToDomainMapper(input.condition),
            maxTempC = input.maxTempC,
            maxWindKph = input.maxWindKph,
            minTempC = input.minTempC,
            totalPrecipitationMm = input.totalPrecipitationMm
        )

}