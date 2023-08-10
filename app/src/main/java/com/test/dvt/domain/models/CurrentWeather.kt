package com.test.dvt.domain.models

data class CurrentWeather(
    val timezone: Int,
    val lat: Double,
    val lon: Double,
    val pressure: Int,
    val base: String,
    val main: String,
    val description: String,
    val icon: String,
    val temp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Int,
    val seaLevel: Int,
    val groundLevel: Int,
    val countryName: String,
    val dt: Int,
)