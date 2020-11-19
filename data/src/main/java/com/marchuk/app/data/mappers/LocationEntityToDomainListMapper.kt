package com.marchuk.app.data.mappers

import com.marchuk.app.core.database.LocationEntity
import com.marchuk.app.core.utils.mappers.ListMapper
import com.marchuk.app.domain.models.Location

class LocationEntityToDomainListMapper(
    private val mapper: LocationEntityToDomainMapper
) : ListMapper<LocationEntity, Location> {

    override fun invoke(input: List<LocationEntity>): List<Location> {
        return input.map { mapper(it) }
    }

}