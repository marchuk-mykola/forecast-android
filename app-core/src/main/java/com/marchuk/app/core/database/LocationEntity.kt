package com.marchuk.app.core.database

import androidx.room.Entity

@Entity(tableName = "location_entity", primaryKeys = ["lat", "lon"])
data class LocationEntity(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)