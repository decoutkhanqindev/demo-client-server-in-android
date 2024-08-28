package com.example.democlient_serverinandroid.demookhttp.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

class AuthInterceptor(private val jwtTokenManger: JwtTokenManger) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // It is used to intercept and modify HTTP requests or responses before they are sent or received.
        println(">>>>> AuthInterceptor START")

        val request: Request = chain.request()
        val token: String = runBlocking { jwtTokenManger.getTokenFromStorage() }
        val newRequest: Request = request.newBuilder().addHeader(
            name = "Authorization", value = "Bearer $token"
        ).build()

        val response: Response = chain.proceed(newRequest)
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            runBlocking { jwtTokenManger.clearJwtToken() }
            // TODO: Refresh token
            // Have 10 requests => 401
            // Allow 1 refresh token request -> new JWT Token
            // Retry/recall 10 requests with new JWT Token
        }

        println(">>>>> AuthInterceptor END")

        return response
    }
}

