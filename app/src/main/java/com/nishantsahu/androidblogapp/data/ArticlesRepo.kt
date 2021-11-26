package com.nishantsahu.androidblogapp.data

import com.nishantsahu.api.BlogApiClient

object ArticlesRepo {

    val api = BlogApiClient.publicApi
    val authApi = BlogApiClient.authApi

    suspend fun getGlobalFeed() = api.getArticles()
    suspend fun getMyFeed() = authApi.getFeedArticles()
}