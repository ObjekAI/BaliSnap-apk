package com.bangkit.balisnap.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.balisnap.repository.DestinationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val destinationrepo: DestinationRepository) : ViewModel() {

    fun getDestination(lat: Double, lon: Double, radius: Int) =
        destinationrepo.getDestination(lat, lon, radius)


    fun getSearchDestination(name: String) =
        destinationrepo.getSearchDestination(name)

//    fun getLastKnownLocation(onLocationRetrieved: (Location?) -> Unit) {
//        viewModelScope.launch {
//            val location = withContext(Dispatchers.IO) {
//                DestinationRepository.getLastKnownLocation()
//            }
//            onLocationRetrieved(location)
//        }
//    }
}
