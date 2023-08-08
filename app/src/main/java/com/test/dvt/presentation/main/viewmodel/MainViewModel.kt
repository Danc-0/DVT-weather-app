package com.test.dvt.presentation.main.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.test.dvt.Application
import com.test.dvt.core.network_connectivity.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainAppViewModel @Inject constructor(private val connectivityObserver: ConnectivityObserver) :
    ViewModel() {
    // TODO: Check the Network Connection Status and Update Immediately.
    // TODO: Check Location Access Permission and Update the User on it being required.
    // TODO: IF Granted, Access the User's Current Location
    // TODO: Retrieve the Longitude and Latitude
    // TODO: IF NOT Provide a Bottom Sheet and Display the Error.

    fun getConnectionStatus() = flow {
        emit(ConnectivityObserver.NetworkStatus.Unavailable)
        try {
            connectivityObserver.observe().collect { state ->
                emit(state)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}