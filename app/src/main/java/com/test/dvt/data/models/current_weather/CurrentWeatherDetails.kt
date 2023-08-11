package com.test.dvt.data.models.current_weather

import com.test.dvt.domain.models.CurrentWeather

data class CurrentWeatherDetails(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    fun toCurrentWeather(): CurrentWeather {
        return CurrentWeather(
            description = weather[0].description,
            humidity = main.humidity,
            seaLevel = main.sea_level,
            groundLevel = main.grnd_level,
            temp = main.temp,
            maxTemp = main.temp_max,
            minTemp = main.temp_min,
            icon = weather[0].icon,
            countryName = name,
            base = base,
            main = weather[0].main,
            dt = dt,
            lat = coord.lat,
            lon = coord.lon,
            pressure = main.pressure,
            timezone = timezone
        )
    }
}