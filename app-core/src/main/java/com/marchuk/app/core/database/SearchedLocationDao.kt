package com.marchuk.app.core.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchedLocationDao {

    @Query("SELECT * FROM location_entity")
    fun getCities(): Flow<List<LocationEntity>>

    @Query("DELETE FROM location_entity WHERE lat = :lat AND lon = :lon")
    suspend fun delete(lat: Double, lon: Double)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(locationEntity: LocationEntity)

    @Query("SELECT count(*) FROM location_entity")
    suspend fun getCount(): Int

}