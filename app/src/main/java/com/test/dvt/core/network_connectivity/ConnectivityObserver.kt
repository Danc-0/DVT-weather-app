package com.test.dvt.core.network_connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<NetworkStatus>

    enum class NetworkStatus {
        Available, Unavailable, Lost
    }
}