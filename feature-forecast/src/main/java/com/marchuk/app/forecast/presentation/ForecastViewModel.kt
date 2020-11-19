package com.marchuk.app.forecast.presentation

import androidx.lifecycle.viewModelScope
import com.marchuk.app.core.utils.mvi.MviViewModel
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.ForecastItem
import com.marchuk.app.domain.models.Location
import com.marchuk.app.domain.useCase.GetForecastByCoordinatesUseCase
import com.marchuk.app.forecast.presentation.mappers.DayForecastToDailyForecastRecyclerModelMapper
import com.marchuk.app.forecast.presentation.mappers.ForecastItemToCurrentForecastRecyclerModelMapper
import com.marchuk.app.forecast.presentation.mappers.HourlyForecastToRecyclerModelMapper
import com.marchuk.app.forecast.presentation.models.DailyRecyclerForecastsHolder
import com.marchuk.app.forecast.presentation.models.HourlyRecyclerForecastsHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

internal class ForecastViewModel(
    private val initialState: ForecastViewState? = null,
    private val location: Location,
    private val getForecastByCoordinatesUseCase: GetForecastByCoordinatesUseCase,
    private val dayForecastToDailyForecastRecyclerModelMapper: DayForecastToDailyForecastRecyclerModelMapper,
    private val forecastItemToCurrentForecastRecyclerModelMapper: ForecastItemToCurrentForecastRecyclerModelMapper,
    private val hourlyForecastToRecyclerModelMapper: HourlyForecastToRecyclerModelMapper
) : MviViewModel<ForecastViewState, ForecastViewEvent, ForecastViewAction>(
    initialState = initialState ?: ForecastViewState(state = ForecastState.Loading, location = location)
) {

    init {
        loadForecast()
    }

    override fun process(viewAction: ForecastViewAction) {
        super.process(viewAction)
        when (viewAction) {
            ForecastViewAction.ReloadData -> loadForecast()
        }
    }

    private fun loadForecast() {
        viewState = viewState.copy(state = ForecastState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val response = getForecastByCoordinatesUseCase(
                lat = viewState.location.lat,
                lon = viewState.location.lon
            )
            when (response) {
                is Result.Success ->
                    viewState = viewState.copy(
                        state = ForecastState.Loaded,
                        forecastItem = response.body,
                        recyclerList = mapResultToRecyclerModels(response.body)
                    )
                is Result.Error.Api ->
                    viewState = viewState.copy(state = ForecastState.Error.UnknownError)
                is Result.Error.Domain -> {
                    when (response.exception) {
                        is IOException -> {
                            viewState = viewState.copy(state = ForecastState.Error.NetworkError)
                        }
                    }
                }
                is Result.Error.Unknown -> {
                    viewState = viewState.copy(state = ForecastState.Error.UnknownError)
                }
            }
        }
    }

    private fun mapResultToRecyclerModels(forecastItem: ForecastItem): List<DelegateAdapterItem> {
        val currentForecast = forecastItemToCurrentForecastRecyclerModelMapper(forecastItem)
        val hourlyForecasts =
            forecastItem.dailyForecasts.first().hourlyForecastsList.map { hourlyForecastToRecyclerModelMapper(it) }
        val dailyForecasts = forecastItem.dailyForecasts.map { dayForecastToDailyForecastRecyclerModelMapper(it) }
        return listOf(
            currentForecast,
            HourlyRecyclerForecastsHolder(hourlyForecasts),
            DailyRecyclerForecastsHolder(dailyForecasts)
        )
    }


}