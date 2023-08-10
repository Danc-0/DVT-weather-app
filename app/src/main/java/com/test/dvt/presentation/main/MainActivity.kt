package com.test.dvt.presentation.main


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.test.dvt.core.network_connectivity.ConnectivityObserver
import com.test.dvt.core.network_connectivity.NetworkConnectivityObserver
import com.test.dvt.domain.models.Coordinates
import com.test.dvt.presentation.navigation.MainNavigationGraph
import com.test.dvt.presentation.ui.components.SnackBarAppState
import com.test.dvt.presentation.ui.components.rememberSnackBarState
import com.test.dvt.presentation.ui.theme.DVTWeatherTestAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        val latitude = intent.getDoubleExtra("lat", 0.0)
        val longitude = intent.getDoubleExtra("lon", 0.0)
        val coordinates = Coordinates(latitude, longitude)
        setContent {
            DVTWeatherTestAppTheme {
                val navController = rememberNavController()
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.NetworkStatus.Unavailable
                )
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    MainComponent(
                        navHostController = navController,
                        coordinates = coordinates,
                        status = status
                    )
                }
            }
        }
    }
}

@Composable
fun MainComponent(
    navHostController: NavHostController,
    coordinates: Coordinates,
    status: ConnectivityObserver.NetworkStatus
) {
    val appState: SnackBarAppState = rememberSnackBarState()
    LaunchedEffect(key1 = true) {
        if (status == ConnectivityObserver.NetworkStatus.Unavailable || status == ConnectivityObserver.NetworkStatus.Lost){
            appState.showSnackBar(message = "Check your network connectivity for real time update")
        }
    }

    Scaffold(
        scaffoldState = appState.scaffoldState
    ){ contentPadding ->
        Column(
            modifier = Modifier.padding(paddingValues = contentPadding)
        ) {
            MainNavigationGraph(
                navController = navHostController,
                coordinates = coordinates,
            )
        }
    }
}


