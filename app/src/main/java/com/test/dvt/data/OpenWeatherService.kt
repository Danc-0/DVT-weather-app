package com.test.dvt.data

import com.test.dvt.data.models.current_forecast.CurrentForecast
import com.test.dvt.data.models.current_weather.CurrentWeatherDetails
import com.test.dvt.data.models.current_weather_forecast.CurrentWeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

//    @GET("weather")
   suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): CurrentWeatherDetails

    @GET("forecast")
    suspend fun getCurrentWeatherForecast(
        @Query(value = "lat") latitude: Double,
        @Query(value = "lon") longitude: Double
    ): CurrentWeatherForecast

    @GET("onecall?exclude=minutely,hourly,alerts&units=metric")
    suspend fun getCurrentForecast(
        @Query(value = "lat") latitude: Double,
        @Query(value = "lon") longitude: Double
    ): CurrentForecast

}