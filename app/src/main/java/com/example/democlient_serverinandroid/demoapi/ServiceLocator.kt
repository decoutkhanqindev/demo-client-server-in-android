package com.example.democlient_serverinandroid.demoapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    // Service Locator is a design pattern used to manage and provide dependencies in an application.
    // It works by creating a central object, called a "Service Locator", that is responsible for
    // storing and providing dependencies when requested.

    private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}