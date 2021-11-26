package com.nishantsahu.api

import com.nishantsahu.api.services.BlogApi
import com.nishantsahu.api.services.BlogAuthApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object BlogApiClient {

    var authToken: String? = null

    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                    .header("Authorization", "Token $it")
                    .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://blogapiexpress.herokuapp.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())

    val publicApi = retrofitBuilder
            .client(okHttpBuilder.build())
            .build()
            .create(BlogApi::class.java)

    val authApi = retrofitBuilder
            .client(okHttpBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(BlogAuthApi::class.java)

}