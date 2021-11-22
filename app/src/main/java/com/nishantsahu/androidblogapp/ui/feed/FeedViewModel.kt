package com.nishantsahu.androidblogapp.ui.feed

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantsahu.androidblogapp.data.ArticlesRepo
import com.nishantsahu.api.models.entities.Article
import kotlinx.coroutines.launch

class FeedViewModel:ViewModel() {

    private val _feed = MutableLiveData<List<Article>>()
    val feed : LiveData<List<Article>> = _feed

    fun fetchGlobalFeed(){
        viewModelScope.launch {

            val articles = ArticlesRepo.getGlobalFeed()
            articles.body()?.let {
                _feed.postValue(it.articles)
                Log.d("FEED", "feed fetched ${it.articlesCount}")
            }
        }
    }
}