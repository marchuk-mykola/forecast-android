package com.marchuk.app.domain.useCase

import com.marchuk.app.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteLastSearchedLocationUseCase(private val repository: LocationRepository) {

    suspend operator fun invoke(lat: Double, lon: Double) {
        return withContext(Dispatchers.IO) {
            repository.deleteLocation(lat = lat, lon = lon)
        }
    }

}