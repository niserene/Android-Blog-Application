package com.nishantsahu.androidblogapp.data

import com.nishantsahu.api.BlogApiClient
import com.nishantsahu.api.models.entities.User
import com.nishantsahu.api.models.requests.*
import com.nishantsahu.api.models.responses.UserResponse

object UserRepo {

    val api = BlogApiClient.publicApi
    val authApi = BlogApiClient.authApi

    suspend fun login(email:String, password:String): UserResponse? {

        val response = api.loginUser(LoginRequest(UserLogin(email, password)))
        BlogApiClient.authToken = response.body()?.user?.token

        return response.body()
    }
    suspend fun signup(email:String, username:String, password: String): UserResponse? {

        val response = api.signupUser(SignupRequest(UserSignup(
            email = email,
            password = password,
            username = username
        )))
        BlogApiClient.authToken = response.body()?.user?.token
        return response.body()
    }
    suspend fun getUserProfile() = authApi.getCurrentUser().body()?.user

    suspend fun updateUser(
            username:String,
            email:String,
            password: String,
            bio:String,
            image:String
    ): User? {
        val response = authApi.updateCurrentUser(UserUpdateRequest(
                        UserUpdate(username, email, password, bio, image)))
        return response.body()?.user
    }

    suspend fun getCurrentUser(token:String):User? {
        BlogApiClient.authToken = token
        return authApi.getCurrentUser().body()?.user

    }
}