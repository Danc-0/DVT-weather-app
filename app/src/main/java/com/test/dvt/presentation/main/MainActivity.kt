package com.test.dvt.presentation.main


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.test.dvt.presentation.navigation.MainNavigationGraph
import com.test.dvt.presentation.ui.theme.DVTWeatherTestAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val latitude = intent.getDoubleExtra("lat", 0.0)
        val longitude = intent.getDoubleExtra("lon", 0.0)
        setContent {
            DVTWeatherTestAppTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainComponent(
                        navHostController = navController,
                        lon = longitude,
                        lat = latitude
                    )
                }
            }
        }
    }
}

@Composable
fun MainComponent(
    navHostController: NavHostController,
    lon: Double,
    lat: Double
) {
    Scaffold() { contentPadding ->
        Column(
            modifier = Modifier.padding(paddingValues = contentPadding)
        ) {
            MainNavigationGraph(navController = navHostController, lon = lon, lat = lat)
        }
    }
}


