package com.test.dvt.presentation.ui.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SnackBarAppState(
    val scaffoldState: ScaffoldState,
    val snackBarScope: CoroutineScope,
    val navController: NavHostController
) {
    fun showSnackBar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        snackBarScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                duration = duration
            )
        }
    }
}

@Composable
fun rememberSnackBarState(
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember {
            SnackbarHostState()
        }
    ),
    navController: NavHostController = rememberNavController(),
    snackBarScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navController, snackBarScope) {
    SnackBarAppState(
        scaffoldState = scaffoldState,
        navController = navController,
        snackBarScope = snackBarScope
    )
}