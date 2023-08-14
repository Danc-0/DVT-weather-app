package com.test.dvt.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.dvt.data.datasource.local.dao.WeatherDao
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather

@Database(
    entities = [
        SavedCurrentWeather::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}