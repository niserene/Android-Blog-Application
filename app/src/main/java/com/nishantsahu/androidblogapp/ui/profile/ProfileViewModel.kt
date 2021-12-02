package com.nishantsahu.androidblogapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantsahu.api.BlogApiClient
import com.nishantsahu.api.models.entities.Profile
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModel() {

    val api = BlogApiClient.publicApi
    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    fun getProfile(username:String) = viewModelScope.launch{

        val profile = api.getProfile(username)
        profile.body()?.let {
            _profile.postValue(it.profile)
        }
    }
}