package com.marchuk.app.data.mappers

import com.marchuk.app.core.database.LocationEntity
import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.domain.models.Location

class LocationDomainToEntityMapper : Mapper<Location, LocationEntity> {

    override fun invoke(input: Location): LocationEntity =
        LocationEntity(
            id = input.id,
            name = input.name,
            region = input.region,
            country = input.country,
            lat = input.lat,
            lon = input.lon
        )

}

