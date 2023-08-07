package com.test.dvt.core.network_connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>
}

enum class Status {
    Available, Unavailable, Lost
}