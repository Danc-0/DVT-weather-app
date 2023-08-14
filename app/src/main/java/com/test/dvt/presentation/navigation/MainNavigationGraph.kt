package com.test.dvt.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.dvt.domain.models.Coordinates
import com.test.dvt.presentation.screens.WeatherScreen

@SuppressLint("MissingPermission")
@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    coordinates: Coordinates,
) {
    NavHost(
        navController = navController,
        startDestination = MainNavRoutes.Weather.route
    ) {
        composable(route = MainNavRoutes.Weather.route) {
            WeatherScreen(
                coordinates = coordinates
            )
        }
    }
}

