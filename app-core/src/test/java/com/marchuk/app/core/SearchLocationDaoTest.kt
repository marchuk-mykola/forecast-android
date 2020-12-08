package com.marchuk.app.core

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.marchuk.app.core.database.ForecastDatabase
import com.marchuk.app.core.database.LocationEntity
import com.marchuk.app.core.database.SearchedLocationDao
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch

@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class SearchLocationDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @MockK
    lateinit var database: ForecastDatabase

    @MockK
    lateinit var searchedLocationDao: SearchedLocationDao

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        database = ForecastDatabase.buildDatabase(ApplicationProvider.getApplicationContext())
        searchedLocationDao = database.searchedCityDao()
    }

    @After
    fun closeDatabase() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @OptIn(InternalCoroutinesApi::class)
    @Test
    fun `insert multiple locations with same keys to forecast dao `() {
        testScope.runBlockingTest {
            searchedLocationDao.insert(
                LocationEntity(
                    3,
                    "Not Constantinople",
                    region = "Region",
                    country = "Turkey",
                    lat = 50.0,
                    lon = -10.0
                )
            )

            searchedLocationDao.insert(
                LocationEntity(
                    3,
                    "Istanbul",
                    region = "Region",
                    country = "Turkey",
                    lat = 50.0,
                    lon = -10.0
                )
            )

            val latch = CountDownLatch(1)
            val job = launch(Dispatchers.IO) {
                searchedLocationDao.getCities().collect { cities: List<LocationEntity> ->
                    Truth.assertThat(cities.size).isEqualTo(1)
                    Truth.assertThat(cities.first().name).isEqualTo("Istanbul")
                    latch.countDown()
                }
            }
            latch.await()
            job.cancel()
        }
    }

    @Test
    fun `insert and delete location and count must be zero`() {
        testScope.runBlockingTest {
            val entity = LocationEntity(
                id = 0,
                name = "",
                region = "",
                country = "",
                lat = -10.0,
                lon = 10.0
            )

            searchedLocationDao.insert(entity)
            val count = searchedLocationDao.getCount()
            Truth.assertThat(count).isEqualTo(1)

            searchedLocationDao.delete(
                lat = entity.lat,
                lon = entity.lon
            )
            val newCount = searchedLocationDao.getCount()
            Truth.assertThat(newCount).isEqualTo(0)
        }
    }


}