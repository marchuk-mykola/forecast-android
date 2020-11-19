package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.DayForecastHolderResponse
import com.marchuk.app.domain.models.DayForecastHolder

internal class DayForecastHolderResponseToDomainMapper(
    private val dailyForecastResponseToDomainMapper: DailyForecastResponseToDomainMapper,
    private val hourlyForecastResponseToDomainListMapper: HourlyForecastResponseToDomainListMapper
) : Mapper<DayForecastHolderResponse, DayForecastHolder> {

    override fun invoke(input: DayForecastHolderResponse): DayForecastHolder =
        DayForecastHolder(
            date = input.date,
            dateEpoch = input.dateEpoch,
            day = dailyForecastResponseToDomainMapper(input.day),
            hourlyForecastsList = hourlyForecastResponseToDomainListMapper(input.hourForecast)
        )

}