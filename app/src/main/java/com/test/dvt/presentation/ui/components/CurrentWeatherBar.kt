package com.test.dvt.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.dvt.R
import com.test.dvt.core.utils.getWeatherName
import com.test.dvt.presentation.ui.theme.White
import java.util.Locale

@Composable
fun CurrentWeatherBar(currentTemp: String, weatherId: Int?) {
    Box(
        modifier = Modifier
            .fillMaxWidth().height(350.dp),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.forest_cloudy),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop
        )
        Column {
            MainItems(currentTemp = currentTemp, weatherId = weatherId)
            Spacer(Modifier.size(4.dp))
        }
    }
}

@Composable
fun MainItems(currentTemp: String, weatherId: Int?) {
    val currentTempDesc = getWeatherName(weatherId)
    val temp = "$currentTemp\u00B0"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = temp,
            color = White,
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Text(
            text = currentTempDesc.uppercase(Locale.ROOT),
            color = White,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}