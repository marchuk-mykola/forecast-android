package com.marchuk.app.forecast.presentation.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.domain.models.ForecastItem
import com.marchuk.app.forecast.presentation.models.CurrentForecastRecyclerModel

class ForecastItemToCurrentForecastRecyclerModelMapper : Mapper<ForecastItem, CurrentForecastRecyclerModel> {

    override fun invoke(input: ForecastItem): CurrentForecastRecyclerModel {
        return CurrentForecastRecyclerModel(
            condition = input.currentForecast.condition,
            feelsLikeC = input.currentForecast.feelsLikeC,
            isDay = input.currentForecast.isDay,
            lastUpdated = input.currentForecast.lastUpdated,
            tempC = input.currentForecast.tempC,
            maxTemp = input.dailyForecasts.first().day.maxTempC,
            minTemp = input.dailyForecasts.first().day.minTempC,
            cityName = input.location.name
        )
    }
}