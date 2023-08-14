package com.test.dvt.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.dvt.presentation.ui.theme.White

@Composable
fun CurrentTemperatureDetails(current: Double?, max: Double?, min: Double?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SingleTempItem(temp = min, description = "Min")
        SingleTempItem(temp = current, description = "Current")
        SingleTempItem(temp = max, description = "Max")
    }
}

@Composable
fun SingleTempItem(temp: Double?, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        Text(text = temp.toString(), color = White)
        Text(text = description, color = White)
        Spacer(modifier = Modifier.size(10.dp))
    }
}