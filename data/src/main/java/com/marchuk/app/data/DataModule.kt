package com.marchuk.app.data

import com.marchuk.app.core.utils.network.retrofit.provideApi
import com.marchuk.app.data.api.AutoCompleteApi
import com.marchuk.app.data.api.ForecastApi
import com.marchuk.app.data.mappers.*
import com.marchuk.app.data.repository.ForecastRepositoryImpl
import com.marchuk.app.data.repository.LocationRepositoryImpl
import com.marchuk.app.domain.repository.ForecastRepository
import com.marchuk.app.domain.repository.LocationRepository
import org.koin.dsl.module

val dataModule = module {
    single { provideApi<AutoCompleteApi>(get()) }
    single { provideApi<ForecastApi>(get()) }

    factory { ConditionResponseToDomainMapper() }
    factory { CurrentForecastResponseToDomainMapper(get()) }
    factory { DailyForecastResponseToDomainListMapper(get()) }
    factory { DailyForecastResponseToDomainMapper(get()) }
    factory { DayForecastHolderResponseToDomainListMapper(get()) }
    factory { DayForecastHolderResponseToDomainMapper(get(), get()) }
    factory { ForecastItemResponseToDomainMapper(get(), get(), get()) }
    factory { HourlyForecastResponseToDomainListMapper(get()) }
    factory { HourlyForecastResponseToDomainMapper(get()) }
    factory { LocationDomainToEntityMapper() }
    factory { LocationEntityToDomainListMapper(get()) }
    factory { LocationEntityToDomainMapper() }
    factory { LocationResponseToDomainListMapper(get()) }
    factory { LocationResponseToDomainMapper() }
    factory { LocationResponseToEntityMapper() }

    single<LocationRepository> { LocationRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<ForecastRepository> { ForecastRepositoryImpl(get(), get(), get(), get()) }

}