package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.CurrentForecastResponse
import com.marchuk.app.domain.models.CurrentForecast

internal class CurrentForecastResponseToDomainMapper(
    private val conditionResponseToDomainMapper: ConditionResponseToDomainMapper
) : Mapper<CurrentForecastResponse, CurrentForecast> {

    override fun invoke(input: CurrentForecastResponse): CurrentForecast =
        CurrentForecast(
            cloud = input.cloud,
            condition = conditionResponseToDomainMapper(input.condition),
            feelsLikeC = input.feelsLikeC,
            gustKph = input.gustKph,
            humidity = input.humidity,
            isDay = input.isDay == 1,
            lastUpdated = input.lastUpdated,
            lastUpdatedEpoch = input.lastUpdatedEpoch,
            precipitationMm = input.precipMm,
            pressureMb = input.pressureMb,
            tempC = input.tempC,
            windKph = input.windKph
        )
}