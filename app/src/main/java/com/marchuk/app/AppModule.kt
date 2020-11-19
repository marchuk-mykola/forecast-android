package com.marchuk.app

import com.marchuk.app.feature_search.presentation.SearchNavigator
import org.koin.dsl.binds
import org.koin.dsl.module

val appModule = module {
    single { AppNavigator() }.binds(
        arrayOf(SearchNavigator::class)
    )
}