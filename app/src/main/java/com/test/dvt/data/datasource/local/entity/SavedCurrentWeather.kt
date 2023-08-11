package com.test.dvt.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
class SavedCurrentWeather(
    @PrimaryKey
    @ColumnInfo(name = "timezone")
    val timezone: Int,

    @ColumnInfo(name = "locationName")
    val name: String,

    @ColumnInfo(name = "latitude")
    val lat: Double,

    @ColumnInfo(name = "longitude")
    val lon: Double,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "pressure")
    val pressure: Int,

    @ColumnInfo(name = "seaLevel")
    val sea_level: Int,

    @ColumnInfo(name = "temperature")
    val temp: Double,

    @ColumnInfo(name = "maximumTemp")
    val temp_max: Double,

    @ColumnInfo(name = "minimumTemp")
    val temp_min: Double

)