package com.bangkit.balisnap.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.liveData
import com.bangkit.balisnap.api.ApiService
import com.bangkit.balisnap.response.DestinationResponse
import com.bangkit.balisnap.response.FoodResponse
import com.bangkit.balisnap.utils.Result
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class DestinationRepository private constructor(
    context: Context,
    private val apiService: ApiService,


    ) {
    private val appContext = context.applicationContext
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext)

    fun getFoods() = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.getFoods()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getDestination(lat : Double, lon : Double, radius : Int) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDestination(lat, lon, radius)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    suspend fun getNearbyDestinations(latitude: Double, longitude: Double, radius: Int): DestinationResponse {
        return apiService.getDestination(latitude, longitude, radius)
    }

    suspend fun getSearchDestination(name: String): DestinationResponse {
        return apiService.getSearchDestination(name)
    }

    suspend fun getLastKnownLocation(): Location? {
        return try {
            if (ContextCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.lastLocation.await()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        @Volatile
        private var instance: DestinationRepository? = null
        fun getInstance(
            context: Context,
            apiService: ApiService

        ): DestinationRepository =
            instance ?: synchronized(this) {
                instance ?: DestinationRepository(context, apiService)
            }.also { instance = it }
    }


}