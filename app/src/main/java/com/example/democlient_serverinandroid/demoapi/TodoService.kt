package com.example.democlient_serverinandroid.demoapi

import retrofit2.http.GET

interface TodoService {
    // contains all methods like GET, POST, PUT, DELETE, .....
    // https://jsonplaceholder.typicode.com/todos/1
    // https://jsonplaceholder.typicode.com -> domain or url
    // todos/1 -> endpoint

    @GET("/todo/1")
    fun getTodo()
}