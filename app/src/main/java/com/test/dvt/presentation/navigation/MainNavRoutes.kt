package com.test.dvt.presentation.navigation

sealed class MainNavRoutes(
    val route: String,
    val title: String,
) {

    object Splash : MainNavRoutes(
        route = "splash_screen",
        title = "Splash",
    )

    object Weather : MainNavRoutes(
        route = "weather_screen",
        title = "Weather",
    )
}