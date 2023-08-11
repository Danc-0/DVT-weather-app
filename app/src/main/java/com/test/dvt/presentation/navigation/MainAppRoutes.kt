package com.test.dvt.presentation.navigation

sealed class MainAppRoutes(
    val route: String,
    val title: String,
) {

    object WeatherApp: MainAppRoutes(
        route = "people_details",
        title = "Star Details"
    )

}