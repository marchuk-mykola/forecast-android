package com.marchuk.app.domain.useCase

import com.marchuk.app.domain.repository.LocationRepository

class DeleteLastSearchedLocationUseCase(private val repository: LocationRepository) {

    suspend operator fun invoke(lat: Double, lon: Double) {
        repository.deleteLocation(lat = lat, lon = lon)
    }

}