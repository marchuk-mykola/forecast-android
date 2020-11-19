package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.HourlyForecastResponse
import com.marchuk.app.domain.models.HourlyForecast

internal class HourlyForecastResponseToDomainMapper(
    private val conditionResponseToDomainMapper: ConditionResponseToDomainMapper
) : Mapper<HourlyForecastResponse, HourlyForecast> {

    override fun invoke(input: HourlyForecastResponse): HourlyForecast =
        HourlyForecast(
            cloud = input.cloud,
            condition = conditionResponseToDomainMapper(input.condition),
            feelsLikeC = input.feelsLikeC,
            gustKph = input.gustKph,
            heatIndexC = input.heatIndexC,
            humidity = input.humidity,
            isDay = input.isDay == 1,
            precipitationMm = input.precipitationMm,
            pressureMb = input.pressureMb,
            tempC = input.tempC,
            time = input.time,
            timeEpoch = input.timeEpoch,
            visibilityKm = input.visibilityKm,
            windKph = input.windKph,
            windChillC = input.windChillC
        )

}