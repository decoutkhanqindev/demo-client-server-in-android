package com.example.democlient_serverinandroid.demoapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoService {
    // contains all methods like GET, POST, PUT, DELETE, .....
    // https://jsonplaceholder.typicode.com/todos/1
    // https://jsonplaceholder.typicode.com -> domain or url
    // todos/1 -> endpoint

    companion object {
        // provide retrofit to call api
        fun retrofitProvider(retrofit: Retrofit): TodoService =
            retrofit.create(TodoService::class.java)
    }

    @GET("/todos/1")
    fun getTodo(): Call<TodoResponse>

    @GET("/todos/1")
    suspend fun getTodoSuspend(): TodoResponse

    @GET("/todos")
    suspend fun getTodos(): List<TodoResponse>

    @POST("/posts")
    suspend fun postNewPost(@Body newPost: NewPost): PostResponse
}