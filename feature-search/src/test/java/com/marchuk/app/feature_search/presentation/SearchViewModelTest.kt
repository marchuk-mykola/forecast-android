package com.marchuk.app.feature_search.presentation

import android.os.Build
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.core.utils.network.baseError.ApiError
import com.marchuk.app.core.utils.network.baseError.ErrorResponse
import com.marchuk.app.domain.useCase.DeleteLastSearchedLocationUseCase
import com.marchuk.app.domain.useCase.GetLastSearchedLocationsUseCase
import com.marchuk.app.domain.useCase.SearchLocationsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class SearchViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var searchedLocationsUseCase: SearchLocationsUseCase

    @MockK
    private lateinit var getLastSearchedLocationsUseCase: GetLastSearchedLocationsUseCase

    @MockK
    private lateinit var deleteLastSearchedLocationUseCase: DeleteLastSearchedLocationUseCase

    @MockK
    private lateinit var navigator: SearchNavigator

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(
            null,
            getLastSearchedLocationsUseCase,
            searchedLocationsUseCase,
            deleteLastSearchedLocationUseCase,
            navigator
        )
    }

    @Test
    fun firstTest() {
        mainCoroutineRule.runBlockingTest {
            val viewStateObserver: Observer<SearchViewState> = mockk(relaxUnitFun = true)
            viewModel.viewStates().observeForever(viewStateObserver)

            coEvery { searchedLocationsUseCase.invoke(any()) } returns Result.Error.Domain(IllegalArgumentException())

            // when
            viewModel.process(SearchViewAction.OnInputChanged(String()))

            Truth.assertThat(viewModel.viewState.state).isEqualTo(SearchState.Error.ValidationError)
            viewModel.viewStates().removeObserver(viewStateObserver)
        }
    }

    @Test
    fun `second test`() {
        mainCoroutineRule.runBlockingTest {
            val viewStateObserver: Observer<SearchViewState> = mockk(relaxUnitFun = true)
            viewModel.viewStates().observeForever(viewStateObserver)

            val viewEventObserver: Observer<SearchViewEffect> = mockk(relaxUnitFun = true)
            viewModel.viewEffects().observeForever(viewEventObserver)

            val result = Result.Error.Api(
                ErrorResponse(
                    ApiError(
                        code = 0, message = "Message"
                    )
                )
            )

            val expected = SearchViewEffect.ShowError(result.body.error.message)

            coEvery { searchedLocationsUseCase.invoke(any()) } returns result

            // when
            viewModel.process(SearchViewAction.OnInputChanged(String()))

            Truth.assertThat(viewModel.viewState.state).isEqualTo(SearchState.Error.ApiError)
            viewModel.viewStates().removeObserver(viewStateObserver)
            Truth.assertThat(viewModel.viewEffect).isEqualTo(expected)
            viewModel.viewEffects().removeObserver(viewEventObserver)

        }
    }

}