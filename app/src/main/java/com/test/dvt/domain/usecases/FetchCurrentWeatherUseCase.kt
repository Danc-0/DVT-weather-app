package com.test.dvt.domain.usecases

import com.test.dvt.core.utils.Resource
import com.test.dvt.data.models.current_forecast.CurrentForecast
import com.test.dvt.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCurrentWeatherUseCase @Inject constructor(private val currentWeatherRepository: CurrentWeatherRepository) {

    suspend operator fun invoke(lat: Double, long: Double): Flow<Resource<CurrentForecast>> {
        return currentWeatherRepository.getCurrentWeather(lat = lat, long = long)
    }

}