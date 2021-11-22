package com.nishantsahu.api

import com.nishantsahu.api.models.requests.SignupRequest
import com.nishantsahu.api.models.requests.UserSignup
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BlogApiClientTest {

    private val blogApiClient = BlogApiClient()

    @Test
    fun getArticles(){

        runBlocking {
            val articles = blogApiClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }
    @Test
    fun getArticlesByAuthor(){

        runBlocking {
            val articles = blogApiClient.api.getArticles(author = "dragons")
            assertNotNull(articles.body()?.articles)
        }
    }
    @Test
    fun getArticlesByTags(){

        runBlocking {
            val articles = blogApiClient.api.getArticles(tags = "dragons")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun createUser(){

        val userCreds = UserSignup(
            "u3@gmail.com",
            "1234",
            "u3"
        )
        runBlocking {
            val resp = blogApiClient.api.signupUser(SignupRequest(userCreds))
            assertEquals(userCreds.username, resp.body()?.user?.username)
        }
    }

}