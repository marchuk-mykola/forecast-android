package com.marchuk.app.data.mappers

import com.marchuk.app.core.database.LocationEntity
import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.domain.models.Location

class LocationEntityToDomainMapper : Mapper<LocationEntity, Location> {

    override fun invoke(input: LocationEntity): Location =
        Location(
            id = input.id,
            name = input.name,
            region = input.region,
            country = input.country,
            lat = input.lat,
            lon = input.lon
        )

}