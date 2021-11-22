package com.nishantsahu.api

import com.nishantsahu.api.services.BlogApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BlogApiClient {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://blogapiexpress.herokuapp.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(BlogApi::class.java)
}