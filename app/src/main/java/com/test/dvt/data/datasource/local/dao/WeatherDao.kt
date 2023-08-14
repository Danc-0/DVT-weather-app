package com.test.dvt.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeather(vararg weather: SavedCurrentWeather)

    @Query("SELECT * FROM current_weather_forecast")
    suspend fun getSavedWeather(): SavedCurrentWeather

    @Delete
    suspend fun deleteWeather(user: SavedCurrentWeather)

}