package com.test.dvt.presentation.viewmodels

import CurrentWeatherState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.dvt.core.utils.Resource
import com.test.dvt.core.utils.UIEvent
import com.test.dvt.data.models.current_forecast.CurrentForecast
import com.test.dvt.domain.usecases.FetchCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: FetchCurrentWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CurrentWeatherState<CurrentForecast>())
    val state: StateFlow<CurrentWeatherState<CurrentForecast>> = _state

    private val _events = MutableSharedFlow<UIEvent>()
    val eventFlow = _events.asSharedFlow()

    private var currentWeatherJob: Job? = null

    fun getCurrentWeather(lat: Double, lon: Double) {
        currentWeatherJob?.cancel()
        currentWeatherJob = viewModelScope.launch {
            getCurrentWeatherUseCase(lat = lat, long = lon).onEach { weather ->
                if (weather.toString().isNotEmpty()) {
                    when (weather) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                weather = weather.data,
                                isLoading = true
                            )
                            Log.d("TAG", "getCurrentWeather: ${state.value.isLoading}")
                        }

                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                weather = weather.data,
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                weather = weather.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

}