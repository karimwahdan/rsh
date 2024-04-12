package com.karimwahdan.rsh.app.config.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionCheck {
    fun isNetworkAvailable(context:Context): Boolean {
        val service=context.getSystemService(CONNECTIVITY_SERVICE)
        val cm = (service) as ConnectivityManager
        val nw = cm.activeNetwork ?: return false
        val actNw = cm.getNetworkCapabilities(nw) ?: return false
        val internetCapability=NetworkCapabilities.NET_CAPABILITY_INTERNET
        val capabilityValidated=NetworkCapabilities.NET_CAPABILITY_VALIDATED
        val capabilityNotSuspended=NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED
        val transportWifi=NetworkCapabilities.TRANSPORT_WIFI
        val transportCellular=NetworkCapabilities.TRANSPORT_CELLULAR
        val transportBluetooth=NetworkCapabilities.TRANSPORT_BLUETOOTH
        val transportEthernet=NetworkCapabilities.TRANSPORT_ETHERNET
        val hasInternetCapability=actNw.hasCapability(internetCapability)
        val hasValidatedCapability=actNw.hasCapability(capabilityValidated)
        val hasCapabilityNotSuspended=actNw.hasCapability(capabilityNotSuspended)
        val hasWifi=actNw.hasTransport(transportWifi)
        val hasCellular=actNw.hasTransport(transportCellular)
        val hasEthernet=actNw.hasTransport(transportEthernet)
        val hasBluetooth=actNw.hasTransport(transportBluetooth)
        val fullInternetCapability=hasInternetCapability && hasValidatedCapability && hasCapabilityNotSuspended
        val anyTransport=hasWifi || hasCellular || hasEthernet || hasBluetooth
        return when {
            fullInternetCapability && (anyTransport) -> true
            else -> false
        }
    }
}
