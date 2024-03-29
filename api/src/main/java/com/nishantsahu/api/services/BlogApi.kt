package com.nishantsahu.api.services

import com.nishantsahu.api.models.requests.LoginRequest
import com.nishantsahu.api.models.requests.SignupRequest
import com.nishantsahu.api.models.responses.*
import retrofit2.Response
import retrofit2.http.*

interface BlogApi {

    @POST("users")
    suspend fun signupUser(
            @Body userCreds: SignupRequest
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
            @Body userCreds: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
            @Query("author")author:String? = null,
            @Query("favourited")favourited:String? = null,
            @Query("tags") tags: String?=null
    ) : Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticleBySlug(
            @Path("slug") slug:String
    ): Response<ArticleResponse>

    @GET("profiles/{username}")
    suspend fun getProfile(
            @Path("username")username:String
    ): Response<ProfileResponse>

    @GET("tags")
    suspend fun getTags() : Response<TagsResponse>
}