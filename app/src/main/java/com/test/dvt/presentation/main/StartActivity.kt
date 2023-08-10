package com.test.dvt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.test.dvt.presentation.navigation.StartNavigationGraph
import com.test.dvt.presentation.ui.theme.DVTWeatherTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DVTWeatherTestAppTheme {
                val navController = rememberNavController()
                StartNavigationGraph(navController = navController)
            }
        }
    }
}