package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.LocationResponse
import com.marchuk.app.domain.models.Location

class LocationResponseToDomainMapper : Mapper<LocationResponse, Location> {

    override fun invoke(input: LocationResponse): Location =
        Location(
            id = input.id ?: 0, // Leave it 0, because when mapped to entity it will be auto incremented
            name = input.name,
            region = input.region,
            country = input.country,
            lat = input.lat,
            lon = input.lon
        )

}

