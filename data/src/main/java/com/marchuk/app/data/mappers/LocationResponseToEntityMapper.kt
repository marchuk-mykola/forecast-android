package com.marchuk.app.data.mappers

import com.marchuk.app.core.database.LocationEntity
import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.LocationResponse

internal class LocationResponseToEntityMapper : Mapper<LocationResponse, LocationEntity> {

    override fun invoke(input: LocationResponse): LocationEntity =
        LocationEntity(
            id = input.id ?: 0, // leave it to 0, room will auto increment this field
            name = input.name,
            region = input.region,
            country = input.country,
            lat = input.lat,
            lon = input.lon
        )

}