package com.test.dvt.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.dvt.R
import com.test.dvt.core.utils.WeatherCondition
import com.test.dvt.core.utils.convertUnixTimeToDay
import com.test.dvt.core.utils.getWeatherCondition
import com.test.dvt.core.utils.tempToInt
import com.test.dvt.data.models.current_forecast.Daily

@Composable
fun WeatherForecastDetails(daily: List<Daily>?) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                }
            }
            items(daily?.size ?: 0) {forecast ->
                SingleForecastComponent(dt = daily!![forecast].dt, weatherIconId = daily[forecast].weather[0].id, temp = daily[forecast].temp.day)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun SingleForecastComponent(dt: Long, weatherIconId: Int, temp: Double) {
    val icon = when (getWeatherCondition(weatherIconId)) {
        WeatherCondition.RAINY -> R.drawable.rain
        WeatherCondition.CLOUDY -> R.drawable.partlysunny
        WeatherCondition.SUNNY -> R.drawable.clear
    }

    val tempIntValue = tempToInt(temp)
    val formattedTemp = "$tempIntValue\u00B0"

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = convertUnixTimeToDay(dt))
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Fit
        )
        Text(text = formattedTemp)
    }
}