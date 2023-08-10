data class CurrentWeatherState <T>(
    val weather: T? = null,
    val isLoading: Boolean = false
)