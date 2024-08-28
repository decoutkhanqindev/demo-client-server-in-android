package com.example.democlient_serverinandroid.demookhttp

import com.squareup.moshi.Json
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/*
*   {
      "id": "c5e79118-5c4f-4ad5-a554-8a2516ae42d1",
      "first_name": "Araceli Meyer",
      "last_name": "Kreiger",
      "age": 21
    }
*
*/
@Keep
internal data class Student(
    @Json(name = "id") @SerializedName("id") val id: String, // c5e79118-5c4f-4ad5-a554-8a2516ae42d1
    @Json(name = "first_name") @SerializedName("first_name") val firstName: String, // Araceli Meyer
    @Json(name = "last_name") @SerializedName("last_name") val lastName: String, // Kreiger
    @Json(name = "age") @SerializedName("age") val age: Int // 21
)