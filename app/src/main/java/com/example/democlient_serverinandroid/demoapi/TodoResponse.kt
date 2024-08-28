package com.example.democlient_serverinandroid.demoapi

import com.google.gson.annotations.SerializedName

/*
    object to receive the returned result
    {
        "userId": 1,
        "id": 1,
        "title": "delectus aut autem",
        "completed": false
    }
*/

data class TodoResponse(
    @field:SerializedName("id")
    // key of json obj -> to handle when the app build is released,
    // Proguard will keep the field name -> avoid causing errors when calling api and returning wrong results
    val id: Int? = null,
    @field:SerializedName("completed") val completed: Boolean? = null,
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("userId") val userId: Int? = null
)


