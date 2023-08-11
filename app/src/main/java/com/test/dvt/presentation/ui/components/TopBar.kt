package com.test.dvt.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopBar(navController: NavController, title: String) {
    Row(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back Arrow")
            }
            Text(
                text = title, style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        },
    )
}