package com.marchuk.app.feature_search.presentation

import androidx.lifecycle.viewModelScope
import com.marchuk.app.core.utils.mvi.MviViewModel
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.domain.EmptyInputException
import com.marchuk.app.domain.models.Location
import com.marchuk.app.domain.useCase.DeleteLastSearchedLocationUseCase
import com.marchuk.app.domain.useCase.GetLastSearchedLocationsUseCase
import com.marchuk.app.domain.useCase.SearchLocationsUseCase
import com.marchuk.app.feature_search.presentation.models.FoundedLocation
import com.marchuk.app.feature_search.presentation.models.RememberedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel(
    initialState: SearchViewState? = null,
    private val getLastSearchedLocationsUseCase: GetLastSearchedLocationsUseCase,
    private val searchLocationsUseCase: SearchLocationsUseCase,
    private val deleteLastSearchedLocationUseCase: DeleteLastSearchedLocationUseCase,
    private val navigator: SearchNavigator
) : MviViewModel<SearchViewState, SearchViewEffect, SearchViewAction>(
    initialState = initialState ?: SearchViewState(state = SearchState.ShowSuggestions)
) {

    override val isLoggingEnabled: Boolean = true

    init {
        startObservingLastSearchedLocations()
    }

    override fun process(viewAction: SearchViewAction) {
        super.process(viewAction)
        when (viewAction) {
            is SearchViewAction.OnInputChanged -> searchLocations(viewAction.input)
            is SearchViewAction.OnLocationClicked -> navigator.navigateToForecast(viewAction.location)
            is SearchViewAction.OnDeleteRememberedLocationClicked -> deleteLocation(viewAction.location)
        }
    }

    private fun deleteLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteLastSearchedLocationUseCase(lat = location.lat, lon = location.lon)
        }
    }

    private fun startObservingLastSearchedLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            getLastSearchedLocationsUseCase().collect { list ->
                viewState = viewState.copy(
                    rememberedLocations = list.reversed().map { location -> RememberedLocation(location) })
            }
        }
    }

    private fun searchLocations(input: String) {
        viewState = viewState.copy(state = SearchState.Searching, input = input)
        viewModelScope.launch {
            when (val result = searchLocationsUseCase(input)) {
                is Result.Success -> {
                    viewState = when {
                        result.body.isEmpty() -> {
                            viewState.copy(state = SearchState.EmptySuccess, foundedLocations = emptyList())
                        }
                        else -> {
                            viewState.copy(
                                state = SearchState.Success,
                                foundedLocations = result.body.map { location -> FoundedLocation(location) }
                            )
                        }
                    }
                }
                is Result.Error.Api -> {
                    viewState = viewState.copy(state = SearchState.Error.ApiError)
                    viewEffect = SearchViewEffect.ShowError(result.body.error.message)
                }
                is Result.Error.Domain -> {
                    when (result.exception) {
                        is IllegalArgumentException -> {
                            viewState = viewState.copy(state = SearchState.Error.ValidationError)
                        }
                        is IOException -> {
                            viewState = viewState.copy(state = SearchState.Error.NetworkError)
                        }
                        is EmptyInputException -> {
                            viewState = viewState.copy(state = SearchState.ShowSuggestions)
                        }
                    }
                }
                is Result.Error.Unknown -> viewState = viewState.copy(state = SearchState.Error.UnknownError)
            }
        }
    }

}