package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.ListMapper
import com.marchuk.app.data.models.LocationResponse
import com.marchuk.app.domain.models.Location

class LocationResponseToDomainListMapper(
    private val mapper: LocationResponseToDomainMapper
) : ListMapper<LocationResponse, Location> {

    override fun invoke(input: List<LocationResponse>): List<Location> {
        return input.map { mapper(it) }
    }

}