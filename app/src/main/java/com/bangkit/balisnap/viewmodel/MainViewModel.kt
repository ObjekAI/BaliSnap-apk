package com.bangkit.balisnap.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bangkit.balisnap.repository.DestinationRepository
import com.bangkit.balisnap.response.DestinationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.bangkit.balisnap.utils.Result
import kotlinx.coroutines.withContext

class MainViewModel(private val destinationrepo: DestinationRepository) : ViewModel() {

    fun getDestination(lat: Double, lon: Double, radius: Int) =
        destinationrepo.getDestination(lat, lon, radius)


    fun getSearchDestination(name: String): LiveData<Result<DestinationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = destinationrepo.getSearchDestination(name)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getLastKnownLocation(onLocationRetrieved: (Location?) -> Unit) {
        viewModelScope.launch {
            val location = withContext(Dispatchers.IO) {
                destinationrepo.getLastKnownLocation()
            }
            onLocationRetrieved(location)
        }
    }
}
