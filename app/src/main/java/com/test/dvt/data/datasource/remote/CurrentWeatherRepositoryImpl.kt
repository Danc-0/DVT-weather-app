package com.test.dvt.data.datasource.remote

import com.test.dvt.core.utils.Resource
import com.test.dvt.data.OpenWeatherService
import com.test.dvt.data.datasource.local.dao.WeatherDao
import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather
import com.test.dvt.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class CurrentWeatherRepositoryImpl(
    private val apiService: OpenWeatherService,
    private val weatherDao: WeatherDao
) : CurrentWeatherRepository {
    override suspend fun getCurrentWeather(
        lat: Double,
        long: Double
    ) {
        try {
            val response = apiService.getCurrentForecast(lat, long)
            val currentWeather = SavedCurrentWeather(
                timezone = response.timezone_offset,
                lat = response.lat,
                lon = response.lon,
                currentTemp = response.current.temp,
                currentMaxTemp = response.daily[0].temp.max,
                currentMinTemp = response.daily[0].temp.min,
                description = response.current.weather[0].description,
                daily = response.daily,
                weatherId = response.current.weather[0].id,
            )
            val response11 = weatherDao.getSavedWeather()
            weatherDao.insertWeather(currentWeather)
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }

    override suspend fun getCurrentWeatherFromDB(): Flow<Resource<SavedCurrentWeather?>> = flow {
        emit(Resource.Loading())
        try {
            val response = weatherDao.getSavedWeather()
            emit(Resource.Success(data = response))
        } catch (exception: IOException) {
            emit(Resource.Error(message = "Request failed, try again later"))
        }
    }
}