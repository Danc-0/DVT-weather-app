package com.test.dvt.domain.usecases

import com.test.dvt.data.datasource.local.entity.SavedCurrentWeather
import com.test.dvt.domain.repository.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCurrentWeatherFromDBUseCase @Inject constructor(private val currentWeatherRepository: CurrentWeatherRepository) {

    suspend operator fun invoke() : Flow<SavedCurrentWeather?> {
        return currentWeatherRepository.getCurrentWeatherFromDB()
    }

}