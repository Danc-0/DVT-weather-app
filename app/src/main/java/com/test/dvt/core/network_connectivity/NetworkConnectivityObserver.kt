package com.test.dvt.core.network_connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.test.dvt.core.network_connectivity.ConnectivityObserver.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkConnectivityObserver(
    context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<NetworkStatus> {
        return callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkStatus.Available) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkStatus.Unavailable) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(NetworkStatus.Lost) }
                }
            }

            connectivityManager.registerDefaultNetworkCallback(networkCallback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }.distinctUntilChanged()
    }
}
