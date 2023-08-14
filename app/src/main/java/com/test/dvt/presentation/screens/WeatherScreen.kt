package com.test.dvt.presentation.screens

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.dvt.core.utils.UIEvent
import com.test.dvt.core.utils.WeatherCondition
import com.test.dvt.core.utils.getWeatherCondition
import com.test.dvt.domain.models.Coordinates
import com.test.dvt.presentation.ui.components.CurrentTemperatureDetails
import com.test.dvt.presentation.ui.components.CurrentWeatherBar
import com.test.dvt.presentation.ui.components.WeatherForecastDetails
import com.test.dvt.presentation.ui.theme.Black
import com.test.dvt.presentation.ui.theme.Cloudy
import com.test.dvt.presentation.ui.theme.Rainy
import com.test.dvt.presentation.ui.theme.Sunny
import com.test.dvt.presentation.viewmodels.CurrentWeatherViewModel
import kotlinx.coroutines.flow.collectLatest

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun WeatherScreen(
    coordinates: Coordinates,
    currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    currentWeatherViewModel.getCurrentWeather(
        lat = coordinates.latitude,
        lon = coordinates.longitude
    )
    val viewWeatherState = currentWeatherViewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        currentWeatherViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            if (viewWeatherState.value.isLoading) {
                LoadingItem()
            } else if (viewWeatherState.value.weather != null) {
                val backgroundColor =
                    when (getWeatherCondition(
                        viewWeatherState.value.weather?.current?.weather?.get(
                            0
                        )?.id
                    )) {
                        WeatherCondition.RAINY -> Rainy
                        WeatherCondition.CLOUDY -> Cloudy
                        WeatherCondition.SUNNY -> Sunny
                    }
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(color = backgroundColor),
                ) {

                    Column {
                        CurrentWeatherBar(
                            currentTemp = viewWeatherState.value.weather?.current?.temp.toString(),
                            weatherId = viewWeatherState.value.weather?.current?.weather?.get(
                                0
                            )?.id
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
            }
        }
    )
}

@Composable
fun LoadingItem() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(5.dp),
            strokeWidth = 3.dp,
            color = Black
        )
        Spacer(modifier = Modifier.size(15.dp))
        Text(text = "Loading Please Wait")

    }
}


