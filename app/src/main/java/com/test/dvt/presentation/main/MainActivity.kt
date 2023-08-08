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

import androidx.navigation.compose.rememberNavController
import com.test.dvt.presentation.ui.components.BottomAppBarComponent


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
