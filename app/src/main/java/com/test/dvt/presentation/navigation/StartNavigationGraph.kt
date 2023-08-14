package com.test.dvt.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.dvt.presentation.screens.SplashScreen

@SuppressLint("MissingPermission")
@Composable
fun StartNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainNavRoutes.Splash.route
    ) {
        composable(route = MainNavRoutes.Splash.route) {
            SplashScreen()
        }
    }
}
