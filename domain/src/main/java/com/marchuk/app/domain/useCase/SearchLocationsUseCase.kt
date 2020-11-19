package com.marchuk.app.domain.useCase

import com.marchuk.app.domain.EmptyInputException
import com.marchuk.app.core.utils.network.*
import com.marchuk.app.domain.models.Location
import com.marchuk.app.domain.repository.LocationRepository

class SearchLocationsUseCase(private val repository: LocationRepository) {

    private val regex = Regex("^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*\$")

    suspend operator fun invoke(cityName: String): NetworkResult<List<Location>> {
        return when {
            cityName.isEmpty() -> {
                Result.Error.Domain(EmptyInputException())
            }
            cityName.matches(regex) -> {
                repository.searchLocationsByName(cityName)
            }
            else -> {
                Result.Error.Domain(IllegalArgumentException("City name does not match the expected value"))
            }
        }
    }


}