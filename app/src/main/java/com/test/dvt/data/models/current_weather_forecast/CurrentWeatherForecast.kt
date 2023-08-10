package com.test.dvt.data.models.current_weather_forecast

data class CurrentWeatherForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)