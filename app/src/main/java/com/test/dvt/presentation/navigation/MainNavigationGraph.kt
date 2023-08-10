package com.test.dvt.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.dvt.presentation.screens.WeatherScreen

@SuppressLint("MissingPermission")
@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    lon: Double,
    lat: Double
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoutes.Weather.route
    ) {
        composable(route = BottomNavRoutes.Weather.route) {
            WeatherScreen(
                navController = navController,
                lon = lon,
                lat = lat

            )
        }

        composable(route = BottomNavRoutes.Forecast.route) {
//            ForecastScreen(navController = navController)
        }
    }
}

