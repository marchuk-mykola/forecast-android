package com.marchuk.app.forecast.presentation

import com.marchuk.app.domain.models.Location
import com.marchuk.app.forecast.presentation.mappers.DayForecastToDailyForecastRecyclerModelMapper
import com.marchuk.app.forecast.presentation.mappers.ForecastItemToCurrentForecastRecyclerModelMapper
import com.marchuk.app.forecast.presentation.mappers.HourlyForecastToRecyclerModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val forecastModule = module {
    factory { DayForecastToDailyForecastRecyclerModelMapper() }
    factory { ForecastItemToCurrentForecastRecyclerModelMapper() }
    factory { HourlyForecastToRecyclerModelMapper() }

    viewModel { (initialState: ForecastViewState, location: Location) ->
        ForecastViewModel(
            initialState = initialState,
            location = location,
            getForecastByCoordinatesUseCase = get(),
            dayForecastToDailyForecastRecyclerModelMapper = get(),
            forecastItemToCurrentForecastRecyclerModelMapper = get(),
            hourlyForecastToRecyclerModelMapper = get()
        )
    }
}