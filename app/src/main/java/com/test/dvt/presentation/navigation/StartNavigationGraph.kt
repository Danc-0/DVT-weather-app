package com.test.dvt.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.dvt.presentation.screens.SplashScreen

@Composable
fun StartNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoutes.Splash.route
    ) {
        composable(route = BottomNavRoutes.Splash.route) {
            SplashScreen()
        }
    }
}
