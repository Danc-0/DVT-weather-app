package com.test.dvt.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.dvt.core.utils.HobbiesTypeConverter
import com.test.dvt.data.datasource.local.dao.WeatherDao
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather

@Database(
    entities = [
        SavedCurrentWeather::class,
    ],
    version = 1,
)
@TypeConverters(HobbiesTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}