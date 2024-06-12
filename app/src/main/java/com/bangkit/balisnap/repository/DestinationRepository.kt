package com.bangkit.balisnap.repository

import androidx.lifecycle.liveData
import com.bangkit.balisnap.api.ApiService
import com.bangkit.balisnap.utils.Result

class DestinationRepository private constructor(
    private val apiService: ApiService,

    ) {
    fun getDestination(lat : Double, lon : Double, radius : Int) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDestination(lat, lon, radius)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getSearchDestination(name:String) = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.getSearchDestination(name)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }
    companion object {
        @Volatile
        private var instance: DestinationRepository? = null
        fun getInstance(
            apiService: ApiService
        ): DestinationRepository =
            instance ?: synchronized(this) {
                instance ?: DestinationRepository(apiService)
            }.also { instance = it }
    }
}