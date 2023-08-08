package com.test.dvt.presentation.navigation

sealed class BottomNavRoutes(
    val route: String,
    val title: String,
) {

    object Splash : BottomNavRoutes(
        route = "splash_screen",
        title = "Splash",
    )

    object Home : BottomNavRoutes(
        route = "home_screen",
        title = "Home",
    )

    object Weather : BottomNavRoutes(
        route = "weather_screen",
        title = "Weather",
    )

    object Forecast : BottomNavRoutes(
        route = "forecast_screen",
        title = "Forecast",
    )
}