package com.bangkit.balisnap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.balisnap.repository.AuthRepostiory
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authRepostiory = AuthRepostiory()

    private val _userLiveData = MutableLiveData<FirebaseUser?>()
    val userLiveData: LiveData<FirebaseUser?> = _userLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                var user = authRepostiory.signUp(email, password)
                user = authRepostiory.setDisplayName(name, user)
                _userLiveData.postValue(user)
            } catch (e: Exception) {
                _errorLiveData.postValue(e.message)
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = authRepostiory.signIn(email, password)
                _userLiveData.postValue(user)
            } catch (e: Exception) {
                _errorLiveData.postValue(e.message)
            }
        }
    }
}