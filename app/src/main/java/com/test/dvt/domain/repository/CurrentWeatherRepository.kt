package com.test.dvt.domain.repository

import com.test.dvt.core.utils.Resource
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather
import com.test.dvt.data.models.current_forecast.CurrentForecast
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(lat: Double, long: Double): Flow<Resource<CurrentForecast>>
    suspend fun getCurrentWeatherFromDB(): Flow<SavedCurrentWeather?>
}