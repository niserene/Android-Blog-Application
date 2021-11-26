package com.nishantsahu.androidblogapp.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantsahu.api.BlogApiClient
import com.nishantsahu.api.models.entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    val api = BlogApiClient.publicApi

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun fetchArticle(slug:String) = viewModelScope.launch {

        val response = api.getArticleBySlug(slug)
        response.body()?.article.let {
            _article.postValue(it)
        }
    }
}