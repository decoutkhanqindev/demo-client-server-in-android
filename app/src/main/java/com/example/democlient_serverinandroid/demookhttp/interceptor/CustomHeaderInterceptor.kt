package com.example.democlient_serverinandroid.demookhttp.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CustomHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // It is used to intercept and modify HTTP requests or responses before they are sent or received.
        println(">>>>> CustomHeaderInterceptor START")

        val request: Request = chain.request()
        val newRequest: Request = request.newBuilder().addHeader(
            name = "User-Agent",
            value = "Android/${Build.VERSION.SDK_INT} ${Build.MODEL} ${Build.DEVICE}"
        ).build()

        val response: Response = chain.proceed(newRequest)

        println(">>>>> CustomHeaderInterceptor END")

        return response
    }
}