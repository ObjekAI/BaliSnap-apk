package com.bangkit.balisnap.di

import android.content.Context
import com.bangkit.balisnap.api.ApiConfig
import com.bangkit.balisnap.repository.DestinationRepository

object Injection {
    fun provideRepository(context: Context): DestinationRepository {
        val apiService = ApiConfig.getApiService()
        return DestinationRepository.getInstance(apiService)
    }
}