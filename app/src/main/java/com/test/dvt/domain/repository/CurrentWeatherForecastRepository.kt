package com.test.dvt.domain.repository

interface CurrentWeatherForecastRepository {
    fun getCurrentWeatherForecast(lat: Double, long: Double)
    fun saveCurrentWeatherForecast()
}