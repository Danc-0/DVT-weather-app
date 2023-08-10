package com.test.dvt.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    /// Save the Current Weather
    /// Retrieve the Current Weather.
    /// Update the Current Weather.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveCurrentWeatherDetails(vararg savedWeatherItem: SavedCurrentWeather)

    @Query("SELECT * FROM current_weather")
    fun fetchCurrentWeatherByTimezone(): Flow<SavedCurrentWeather?>

    @Query("DELETE FROM current_weather")
    suspend fun updateCurrentWeather()

}