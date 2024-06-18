package com.bangkit.balisnap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.balisnap.like.LikeDestination
import com.bangkit.balisnap.repository.DestinationRepository

class LikeViewModel (private val destinationrepo: DestinationRepository) : ViewModel() {

    fun getFavoriteUser(): LiveData<List<LikeDestination>> = destinationrepo.getFavoriteUser()

    fun getFavoriteByName(name: String): LiveData<LikeDestination> = destinationrepo.getFavoriteByName(name)

}