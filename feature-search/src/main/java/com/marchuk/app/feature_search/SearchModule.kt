package com.marchuk.app.feature_search

import com.marchuk.app.feature_search.presentation.SearchViewModel
import com.marchuk.app.feature_search.presentation.SearchViewState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { (initialState: SearchViewState?) -> SearchViewModel(initialState, get(), get(), get(), get()) }
}