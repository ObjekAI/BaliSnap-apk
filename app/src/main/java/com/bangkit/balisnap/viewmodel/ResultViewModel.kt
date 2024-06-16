package com.bangkit.balisnap.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bangkit.balisnap.repository.DestinationRepository
import com.bangkit.balisnap.response.DestinationResponse
import com.bangkit.balisnap.utils.Result

class ResultViewModel(private val repository: DestinationRepository) : ViewModel() {

    fun getSearchDestination(name: String): LiveData<Result<DestinationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = repository.getSearchDestination(name)
            Log.d("awwww", response.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("${e.message}"))
        }
    }
    fun getNearbyDestinations(latitude: Double, longitude: Double, radius: Int): LiveData<Result<DestinationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = repository.getNearbyDestinations(latitude, longitude, radius)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

}