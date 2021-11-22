package com.nishantsahu.androidblogapp.data

import com.nishantsahu.api.BlogApiClient

object ArticlesRepo {

    val api = BlogApiClient().api

    suspend fun getGlobalFeed() = api.getArticles()
}