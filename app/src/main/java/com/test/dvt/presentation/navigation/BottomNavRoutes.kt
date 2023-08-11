package com.test.dvt.presentation.navigation

sealed class BottomNavRoutes(
    val route: String,
    val title: String,
) {

    object Splash : BottomNavRoutes(
        route = "splash_screen",
        title = "Splash",
    )

    object Weather : BottomNavRoutes(
        route = "weather_screen",
        title = "Weather",
    )
}