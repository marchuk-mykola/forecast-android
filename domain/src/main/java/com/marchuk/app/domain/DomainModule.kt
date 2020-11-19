package com.marchuk.app.domain

import com.marchuk.app.domain.useCase.DeleteLastSearchedLocationUseCase
import com.marchuk.app.domain.useCase.GetForecastByCoordinatesUseCase
import com.marchuk.app.domain.useCase.GetLastSearchedLocationsUseCase
import com.marchuk.app.domain.useCase.SearchLocationsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetForecastByCoordinatesUseCase(get()) }
    factory { GetLastSearchedLocationsUseCase(get()) }
    factory { SearchLocationsUseCase(get()) }
    factory { DeleteLastSearchedLocationUseCase(get()) }
}