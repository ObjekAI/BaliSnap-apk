package com.bangkit.balisnap.viewmodel

import androidx.lifecycle.ViewModel
import com.bangkit.balisnap.repository.DestinationRepository

class MainViewModel(private val destinationrepo: DestinationRepository) : ViewModel() {

    fun getDestination(lat: Double, lon: Double, radius: Int) =
        destinationrepo.getDestination(lat, lon, radius)


    fun getSearchDestination(name: String) =
        destinationrepo.getSearchDestination(name)
}
