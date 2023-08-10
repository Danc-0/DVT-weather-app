package com.test.dvt.data.datasource.remote

import com.test.dvt.core.utils.Resource
import com.test.dvt.data.OpenWeatherService
import com.test.dvt.data.datasource.local.dao.WeatherDao
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather
import com.test.dvt.data.models.current_forecast.CurrentForecast
import com.test.dvt.domain.models.CurrentWeather
import com.test.dvt.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class CurrentWeatherRepositoryImpl(
    private val apiService: OpenWeatherService,
    private val weatherDao: WeatherDao
) : CurrentWeatherRepository {
    override suspend fun getCurrentWeather(lat: Double, long: Double): Flow<Resource<CurrentForecast>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getCurrentForecast(lat, long)
            emit(Resource.Success(data = response))
        } catch (exception: IOException) {
           emit(Resource.Error(message = "Check your network connection"))
        }
    }

    override suspend fun getCurrentWeatherFromDB(): Flow<SavedCurrentWeather?> {
        return weatherDao.fetchCurrentWeatherByTimezone()
    }
}