package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.ListMapper
import com.marchuk.app.data.models.DayForecastHolderResponse
import com.marchuk.app.domain.models.DayForecastHolder

internal class DayForecastHolderResponseToDomainListMapper(
    private val dayForecastHolderResponseToDomainMapper: DayForecastHolderResponseToDomainMapper
) : ListMapper<DayForecastHolderResponse, DayForecastHolder> {

    override fun invoke(input: List<DayForecastHolderResponse>): List<DayForecastHolder> =
        input.map { dayForecastHolderResponseToDomainMapper(it) }

}