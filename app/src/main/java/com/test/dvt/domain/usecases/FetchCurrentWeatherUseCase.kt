package com.test.dvt.domain.usecases

import com.test.dvt.domain.repository.CurrentWeatherRepository
import javax.inject.Inject

class FetchCurrentWeatherUseCase @Inject constructor(private val currentWeatherRepository: CurrentWeatherRepository) {

    suspend operator fun invoke(lat: Double, long: Double) {
        return currentWeatherRepository.getCurrentWeather(lat = lat, long = long)
    }

}