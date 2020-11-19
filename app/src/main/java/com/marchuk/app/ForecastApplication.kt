package com.marchuk.app

import android.app.Application
import com.marchuk.app.core.database.databaseModule
import com.marchuk.app.core.utils.network.retrofit.retrofitModule
import com.marchuk.app.data.dataModule
import com.marchuk.app.domain.domainModule
import com.marchuk.app.feature_search.searchModule
import com.marchuk.app.forecast.presentation.forecastModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class ForecastApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ForecastApplication)
            modules(
                listOf(
                    appModule,
                    retrofitModule,
                    searchModule,
                    dataModule,
                    domainModule,
                    databaseModule,
                    forecastModule
                )
            )
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}