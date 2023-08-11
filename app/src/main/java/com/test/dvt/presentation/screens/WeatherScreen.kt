package com.test.dvt.presentation.screens

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.dvt.core.utils.WeatherCondition
import com.test.dvt.core.utils.getWeatherCondition
import com.test.dvt.domain.models.Coordinates
import com.test.dvt.presentation.ui.components.CurrentTemperatureDetails
import com.test.dvt.presentation.ui.components.CurrentWeatherBar
import com.test.dvt.presentation.ui.components.WeatherForecastDetails
import com.test.dvt.presentation.ui.theme.Cloudy
import com.test.dvt.presentation.ui.theme.Rainy
import com.test.dvt.presentation.ui.theme.Sunny
import com.test.dvt.presentation.viewmodels.CurrentWeatherViewModel

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun WeatherScreen(
    coordinates: Coordinates,
    currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    currentWeatherViewModel.getCurrentWeather(lat = coordinates.latitude, lon = coordinates.longitude)
    val viewWeatherState = currentWeatherViewModel.state.collectAsState()

    Scaffold(
        content = {
            val color = when (getWeatherCondition(viewWeatherState.value.weather?.current?.weather?.get(0)?.id)) {
                WeatherCondition.RAINY -> Rainy
                WeatherCondition.CLOUDY -> Cloudy
                WeatherCondition.SUNNY -> Sunny
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(color = color),
            ) {
                CurrentWeatherBar(
                    currentTemp = viewWeatherState.value.weather?.current?.temp.toString(),
                    currentTempDesc = viewWeatherState.value.weather?.current?.weather?.get(0)?.description.toString()
                )
                CurrentTemperatureDetails(
                    current = viewWeatherState.value.weather?.current?.temp,
                    min = viewWeatherState.value.weather?.daily?.get(0)?.temp?.min,
                    max = viewWeatherState.value.weather?.daily?.get(0)?.temp?.max,
                )
                Divider(color = Color.White, thickness = 1.dp)
                WeatherForecastDetails(daily = viewWeatherState.value.weather?.daily)

            }
        }
    )
}


