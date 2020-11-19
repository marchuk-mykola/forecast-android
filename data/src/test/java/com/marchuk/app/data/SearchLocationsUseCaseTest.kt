package com.marchuk.app.data

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marchuk.app.domain.EmptyInputException
import com.marchuk.app.core.utils.network.Result
import com.marchuk.app.domain.useCase.SearchLocationsUseCase
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class SearchLocationsUseCaseTest {

    val useCase = SearchLocationsUseCase(mockk())

    @Test
    fun `check SearchLocationsUseCase input validation`() {
        runBlockingTest {
            assert((useCase.invoke("111123") as Result.Error.Domain).exception is java.lang.IllegalArgumentException)
            assert((useCase.invoke("") as Result.Error.Domain).exception is EmptyInputException)
        }
    }

}