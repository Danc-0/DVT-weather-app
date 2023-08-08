package com.test.dvt.presentation.navigation

import android.location.Location
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.dvt.presentation.screens.ForecastScreen
import com.test.dvt.presentation.screens.HomeScreen
import com.test.dvt.presentation.screens.WeatherScreen

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    location: Location?
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoutes.Weather.route
    ) {

        composable(route = BottomNavRoutes.Weather.route) {
            WeatherScreen(navController = navController, location = location)
        }

        composable(route = BottomNavRoutes.Forecast.route) {
            ForecastScreen(navController = navController, location = location)
        }
    }
}