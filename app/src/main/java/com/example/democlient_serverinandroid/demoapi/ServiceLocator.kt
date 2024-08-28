package com.example.democlient_serverinandroid.demoapi

import com.example.democlient_serverinandroid.BuildConfig
import com.example.democlient_serverinandroid.demookhttp.interceptor.AuthInterceptor
import com.example.democlient_serverinandroid.demookhttp.interceptor.CustomHeaderInterceptor
import com.example.democlient_serverinandroid.demookhttp.interceptor.JwtTokenManger
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceLocator {
    // Service Locator is a design pattern used to manage and provide dependencies in an application.
    // It works by creating a central object, called a "Service Locator", that is responsible for
    // storing and providing dependencies when requested.

    // https://jsonplaceholder.typicode.com/todos/1
    // https://jsonplaceholder.typicode.com -> domain or url
    // todos/1 -> endpoint

    private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClientProvider())
            // .addConverterFactory(GsonConverterFactory.create(gsonProvider())).build()
            .addConverterFactory(MoshiConverterFactory.create(moshiProvider())).build()
    }

    // instance retrofit service to call api
    val todoService: TodoService by lazy {
        retrofit.create(TodoService::class.java)
    }

    // OkHttpClient -> modify request
    private fun okHttpClientProvider(): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(timeout = 15, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = 15, unit = TimeUnit.SECONDS)
            .writeTimeout(timeout = 15, unit = TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }).addInterceptor(interceptor = CustomHeaderInterceptor())
            .addInterceptor(interceptor = AuthInterceptor(JwtTokenManger())).build()

    // converter json -> pojo
    private fun moshiProvider(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private fun gsonProvider(): Gson = Gson()
}
