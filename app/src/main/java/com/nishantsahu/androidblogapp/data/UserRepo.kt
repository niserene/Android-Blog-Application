package com.nishantsahu.androidblogapp.data

import com.nishantsahu.api.BlogApiClient
import com.nishantsahu.api.models.requests.LoginRequest
import com.nishantsahu.api.models.requests.UserLogin
import com.nishantsahu.api.models.responses.UserResponse

object UserRepo {

    val api = BlogApiClient().api

    suspend fun login(email:String, password:String): UserResponse? {

        val response = api.loginUser(LoginRequest(UserLogin(email, password)))
        return response.body()
    }
}