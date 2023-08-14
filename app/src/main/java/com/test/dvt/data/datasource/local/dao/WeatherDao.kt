package com.test.dvt.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather

@Dao
interface WeatherDao {

    /// Save the Current Weather
    /// Retrieve the Current Weather.
    /// Update the Current Weather.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeather(vararg users: SavedCurrentWeather)

    @Query("SELECT * FROM current_weather_forecast")
    fun getSavedWeather(): SavedCurrentWeather

    @Delete
    fun deleteWeather(user: SavedCurrentWeather)

}