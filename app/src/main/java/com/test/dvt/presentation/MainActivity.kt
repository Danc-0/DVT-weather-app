package com.test.dvt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.dvt.core.network_connectivity.ConnectivityObserver
import com.test.dvt.core.network_connectivity.NetworkConnectivityObserver
import com.test.dvt.presentation.ui.theme.DVTWeatherTestAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext);
        setContent {
            DVTWeatherTestAppTheme {
                val status by connectivityObserver.observe()
                    .collectAsState(initial = ConnectivityObserver.NetworkStatus.Unavailable
                    )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android Network Status: $status")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DVTWeatherTestAppTheme {
        Greeting("Android")
    }
}