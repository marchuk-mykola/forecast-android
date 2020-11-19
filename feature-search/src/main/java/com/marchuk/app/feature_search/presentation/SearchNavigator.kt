package com.marchuk.app.feature_search.presentation

import com.marchuk.app.domain.models.Location

interface SearchNavigator {
    fun navigateToForecast(location: Location)
}