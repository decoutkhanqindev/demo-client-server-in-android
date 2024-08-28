package com.example.democlient_serverinandroid.demoapi

import com.google.gson.annotations.SerializedName

data class TodoResponse(
    /*
        object to receive the returned result
        {
            "userId": 1,
            "id": 1,
            "title": "delectus aut autem",
            "completed": false
        }
    */

    @field:SerializedName("id") val id: Int? = null,

    @field:SerializedName("completed") val completed: Boolean? = null,

    @field:SerializedName("title") val title: String? = null,

    @field:SerializedName("userId") val userId: Int? = null
)
