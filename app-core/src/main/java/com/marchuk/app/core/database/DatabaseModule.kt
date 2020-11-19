package com.marchuk.app.core.database

import org.koin.dsl.module

val databaseModule = module {
    single { ForecastDatabase.buildDatabase(get()) }
    single { get<ForecastDatabase>().searchedCityDao() }
}