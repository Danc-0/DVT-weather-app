package com.test.dvt.domain.repository

import com.test.dvt.core.utils.Resource
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(lat: Double, long: Double)
    suspend fun getCurrentWeatherFromDB(): Flow<Resource<SavedCurrentWeather?>>
}