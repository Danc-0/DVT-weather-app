package com.test.dvt.presentation.screens

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.test.dvt.presentation.ui.components.TopBar
import com.test.dvt.presentation.viewmodels.CurrentWeatherViewModel

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun WeatherScreen(
    navController: NavController,
    lon: Double,
    lat: Double,
    currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    currentWeatherViewModel.getCurrentWeather(lat = lat, lon = lon)
    val viewWeatherState = currentWeatherViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopBar(navController = navController, "Current Weather")
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    Text(
                        text = viewWeatherState.value.weather.toString(),
                        color = Color.Black
                    )
                }
            }
        }
    )
}


