package com.test.dvt.data.models.current_forecast

data class CurrentForecast(
    val current: Current,
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)