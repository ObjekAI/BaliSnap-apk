package com.bangkit.balisnap.viewmodel

import androidx.lifecycle.ViewModel
import com.bangkit.balisnap.repository.DestinationRepository

class FoodViewModel (private val destinationrepo: DestinationRepository) : ViewModel() {

    fun getFoods()=
        destinationrepo.getFoods()
}