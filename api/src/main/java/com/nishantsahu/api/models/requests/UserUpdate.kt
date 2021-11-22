package com.nishantsahu.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdate(
    @Json(name = "bio")
    val bio: String?=null,
    @Json(name = "email")
    val email: String?=null,
    @Json(name = "image")
    val image: String?=null
)