package com.example.democlient_serverinandroid.demoapi

import com.google.gson.annotations.SerializedName

data class NewPost(
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("body") val body: String? = null,
    @field:SerializedName("userId") val userId: Int? = null
)
