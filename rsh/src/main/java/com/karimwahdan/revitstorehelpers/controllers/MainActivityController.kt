@file:Suppress("unused")

package com.karimwahdan.revitstorehelpers.controllers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class MainActivityController : ViewModel() {
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean>
        get() = _isConnected

    init {
        viewModelScope.launch {
            while (true) {
                delay(2000) // Check connection every 5 seconds
                checkConnection()
            }
        }
    }
    private fun checkConnection() {
        viewModelScope.launch {
            _isConnected.value = isConnected()
        }
    }
    private suspend fun isConnected(): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val socket = Socket()
                socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                socket.close()
                Log.e("isConnected","change: Connected")
                true
            }

        } catch (e: IOException) {
            Log.e("isConnected","change: Not Connected")
            false
        }
    }
}