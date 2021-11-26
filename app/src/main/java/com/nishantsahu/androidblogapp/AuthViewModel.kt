package com.nishantsahu.androidblogapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishantsahu.androidblogapp.data.UserRepo
import com.nishantsahu.api.models.entities.User
import kotlinx.coroutines.launch

class AuthViewModel:ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user:LiveData<User?> = _user

    fun getCurrentUser(token:String) = viewModelScope.launch {
        UserRepo.getCurrentUser(token)?.let {
            _user.postValue(it)
        }
    }

    fun login(email:String, password:String) = viewModelScope.launch {
        UserRepo.login(email, password).let {
            _user.postValue(it?.user)
        }
    }

    fun signup(email:String, username:String, password: String) = viewModelScope.launch {
        UserRepo.signup(email, username, password)?.let{
            _user.postValue(it?.user)
        }
    }

    fun logout(){
        _user.postValue(null)
    }

    fun updateUser(
            username:String,
            email:String,
            password: String,
            bio:String,
            image:String
    ) = viewModelScope.launch {
        UserRepo.updateUser(username, email, password, bio, image)?.let {
            _user.postValue(it)
        }
    }

}