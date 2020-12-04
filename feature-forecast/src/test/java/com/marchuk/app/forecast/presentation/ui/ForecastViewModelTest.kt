package com.marchuk.app.forecast.presentation.ui

import android.os.Build
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.domain.models.*
import com.marchuk.app.domain.useCase.GetForecastByCoordinatesUseCase
import com.marchuk.app.forecast.presentation.mappers.DayForecastToDailyForecastRecyclerModelMapper
import com.marchuk.app.forecast.presentation.mappers.ForecastItemToCurrentForecastRecyclerModelMapper
import com.marchuk.app.forecast.presentation.mappers.HourlyForecastToRecyclerModelMapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class ForecastViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val location: Location = Location(id = 0, name = "", region = "", country = "", lat = 10.0, lon = 5.0)

    @MockK
    lateinit var getForecastByCoordinatesUseCase: GetForecastByCoordinatesUseCase

    private lateinit var viewModel: ForecastViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        val dayForecastToDailyForecastRecyclerModelMapper = DayForecastToDailyForecastRecyclerModelMapper()
        val forecastItemToCurrentForecastRecyclerModelMapper = ForecastItemToCurrentForecastRecyclerModelMapper()
        val hourlyForecastToRecyclerModelMapper = HourlyForecastToRecyclerModelMapper()

        viewModel = ForecastViewModel(
            null,
            location,
            getForecastByCoordinatesUseCase,
            dayForecastToDailyForecastRecyclerModelMapper,
            forecastItemToCurrentForecastRecyclerModelMapper,
            hourlyForecastToRecyclerModelMapper
        )
    }

    @Test
    fun `load data`() {
        mainCoroutineRule.runBlockingTest {
            val viewStateObserver: Observer<ForecastViewState> = mockk(relaxUnitFun = true)
            viewModel.viewStates().observeForever(viewStateObserver)

            coEvery { getForecastByCoordinatesUseCase.invoke(any(), any()) } returns Result.Error.Unknown()

            // when
            viewModel.process(ForecastViewAction.ReloadData)

            Truth.assertThat(viewModel.viewState.state).isEqualTo(ForecastState.Error.UnknownError)
            viewModel.viewStates().removeObserver(viewStateObserver)
//            verify { viewModel.viewStates().value }

            // Then
//            val slots = mutableListOf<ForecastViewState>()
//            verify { viewStateObserver.onChanged(capture(slots)) }
//            Truth.assertThat(slots.last().state).isEqualTo(ForecastState.Error.NetworkError)
//
        }
    }

    @Test
    fun `success`() {
        mainCoroutineRule.runBlockingTest {
            val viewStateObserver: Observer<ForecastViewState> = mockk(relaxUnitFun = true)
            viewModel.viewStates().observeForever(viewStateObserver)

            val forecastItem = getForecastItem()
            coEvery { getForecastByCoordinatesUseCase.invoke(any(), any()) } returns Result.Success(forecastItem)

            // when
            viewModel.process(ForecastViewAction.ReloadData)

            // Then
            val slots = mutableListOf<ForecastViewState>()
            verify { viewStateObserver.onChanged(capture(slots)) }

            Truth.assertThat(slots[0].state).isEqualTo(ForecastState.Loading)
            Truth.assertThat(slots[1].state).isEqualTo(ForecastState.Loading)
            Truth.assertThat(slots.last().state).isEqualTo(ForecastState.Loaded)

            viewModel.viewStates().removeObserver(viewStateObserver)
        }
    }

}

fun getLocation() = Location(id = 0, name = "", region = "", country = "", lat = 0.0, lon = 0.0)


fun getForecastItem(): ForecastItem {
    val location = getLocation()
    val currentForecast = CurrentForecast(
        cloud = 0,
        condition = Condition(iconUrl = "", text = ""),
        feelsLikeC = 0.0,
        gustKph = 0.0,
        humidity = 0,
        isDay = false,
        lastUpdated = "",
        lastUpdatedEpoch = 0,
        precipitationMm = 0.0,
        pressureMb = 0.0,
        tempC = 0.0,
        windKph = 0.0
    )
    val dayForecastHolder = DayForecastHolder(
        date = "", dateEpoch = 0, day = DailyForecast(
            avgHumidity = 0.0,
            avgTempC = 0.0,
            avgVisibilityKm = 0.0,
            condition = Condition(iconUrl = "", text = ""),
            maxTempC = 0.0,
            maxWindKph = 0.0,
            minTempC = 0.0,
            totalPrecipitationMm = 0.0
        ), hourlyForecastsList = listOf()
    )
    return ForecastItem(location, currentForecast, listOf(dayForecastHolder))
}