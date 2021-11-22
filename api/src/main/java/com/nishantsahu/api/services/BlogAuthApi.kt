package com.nishantsahu.api.services

import com.nishantsahu.api.models.requests.UserUpdateRequest
import com.nishantsahu.api.models.responses.ArticleResponse
import com.nishantsahu.api.models.responses.ArticlesResponse
import com.nishantsahu.api.models.responses.ProfileResponse
import com.nishantsahu.api.models.responses.UserResponse
import jdk.nashorn.internal.objects.annotations.Getter
import retrofit2.http.*
import javax.xml.ws.Response

interface BlogAuthApi {

    @GET("user")
    suspend fun getCurrentUser():Response<UserResponse>

    @PATCH("user")
    suspend fun updateCurrentUser(
            @Body userUpdateRequest : UserUpdateRequest
    ): Response<UserResponse>

    @GET("profiles/{username}")
    suspend fun getProfile(
            @Path("username") username: String
    ):Response<ProfileResponse>

    @POST("profiles/{username}/follow")
    suspend fun followProfile(
            @Path("username")username: String
    ): Response<ProfileResponse>

    @DELETE("profiles/{username}/follow")
    suspend fun unfollowProfile(
            @Path("username")username: String
    ): Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
            @Path("slug")slug:String
    ): Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unfavoriteArticle(
            @Path("slug")slug:String
    ): Response<ArticleResponse>
}