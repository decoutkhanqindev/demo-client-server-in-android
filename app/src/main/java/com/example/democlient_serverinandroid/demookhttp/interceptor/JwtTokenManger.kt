package com.example.democlient_serverinandroid.demookhttp.interceptor

import kotlinx.coroutines.delay

class JwtTokenManger {
    // Read from SharedPreferences, Room, etc.
    suspend fun getTokenFromStorage(): String {
        println(">>> JwtTokenManager.getTokenFromStorage")
        delay(200)
        return "OK"
    }

    // Clear from SharedPreferences, Room, etc.
    suspend fun clearJwtToken() {
        println(">>> JwtTokenManager.clearJwtToken")
        delay(200)
    }
}