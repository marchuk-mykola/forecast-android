package com.marchuk.app.domain.useCase

import com.marchuk.app.domain.models.Location
import com.marchuk.app.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetLastSearchedLocationsUseCase(
    private val locationRepository: LocationRepository
) {

    operator fun invoke(): Flow<List<Location>> {
        return locationRepository.getLastSearchedLocations()
    }

}