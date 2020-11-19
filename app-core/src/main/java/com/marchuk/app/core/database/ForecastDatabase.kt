package com.marchuk.app.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun searchedCityDao(): SearchedLocationDao

    companion object {
        private const val DATABASE_NAME = "Forecast Database"

        fun buildDatabase(context: Context): ForecastDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }


}