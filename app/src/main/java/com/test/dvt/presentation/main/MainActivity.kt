package com.test.dvt.presentation.main

import android.content.Context
import android.content.pm.PackageManager
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.test.dvt.presentation.main.viewmodel.MainAppViewModel
import com.test.dvt.presentation.navigation.MainNavigationGraph
import com.test.dvt.presentation.ui.theme.DVTWeatherTestAppTheme
import dagger.hilt.android.AndroidEntryPoint


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.app.ActivityCompat

import androidx.navigation.compose.rememberNavController
import com.test.dvt.Application
import com.test.dvt.presentation.ui.components.BottomAppBarComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val resultCode: Int = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isLocationPermissionGranted(this)
        setContent {
            DVTWeatherTestAppTheme {
                val coroutineScope = rememberCoroutineScope()
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainComponent(navHostController = navController)
                }
            }
        }
    }

    private fun isLocationPermissionGranted(context: Context): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                resultCode
            )
            false
        } else {
            true
        }
    }
}

@Composable
fun MainComponent(navHostController: NavHostController) {
    Scaffold(modifier = Modifier, bottomBar = {
        BottomAppBarComponent(navController = navHostController)
    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(paddingValues = contentPadding)
        ) {
            MainNavigationGraph(navController = navHostController)
        }
    }
}


