package com.marchuk.app.forecast.presentation.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.domain.models.DayForecastHolder
import com.marchuk.app.forecast.presentation.models.DailyForecastRecyclerModel

class DayForecastToDailyForecastRecyclerModelMapper : Mapper<DayForecastHolder, DailyForecastRecyclerModel> {

    override fun invoke(input: DayForecastHolder): DailyForecastRecyclerModel {
        return DailyForecastRecyclerModel(
            time = input.date,
            maxTempC = input.day.maxTempC,
            minTempC = input.day.minTempC,
            condition = input.day.condition
        )
    }

}