package com.marchuk.app.forecast.presentation.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.domain.models.HourlyForecast
import com.marchuk.app.forecast.presentation.models.HourlyForecastRecyclerModel

class HourlyForecastToRecyclerModelMapper : Mapper<HourlyForecast, HourlyForecastRecyclerModel> {

    override fun invoke(input: HourlyForecast): HourlyForecastRecyclerModel {
        return HourlyForecastRecyclerModel(
            time = input.time,
            tempC = input.tempC
        )
    }

}