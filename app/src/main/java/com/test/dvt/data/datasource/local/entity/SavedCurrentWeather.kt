package com.test.dvt.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.dvt.data.models.current_forecast.Daily

@Entity(tableName = "current_weather_forecast")
class SavedCurrentWeather(
    @PrimaryKey
    @ColumnInfo(name = "timezone")
    val timezone: Int,

    @ColumnInfo(name = "latitude")
    val lat: Double,

    @ColumnInfo(name = "longitude")
    val lon: Double,

    @ColumnInfo(name = "humidity")
    val currentTemp: Int,

    @ColumnInfo(name = "currentMinTemp")
    val currentMinTemp: Int,

    @ColumnInfo(name = "currentMaxTemp")
    val currentMaxTemp: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "icon")
    val daily: List<Daily>

)