package com.bangkit.balisnap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.balisnap.like.LikeDestination
import com.bangkit.balisnap.repository.DestinationRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val userRepositoryUser: DestinationRepository) : ViewModel() {

    fun getFavoriteByName(name: String): LiveData<LikeDestination> = userRepositoryUser.getFavoriteByName(name)

    fun insert(likeDestination: LikeDestination) = viewModelScope.launch {
        userRepositoryUser.insert(likeDestination)
    }

    fun delete(likeDestination: LikeDestination) = viewModelScope.launch {
        userRepositoryUser.delete(likeDestination)
    }
}