package com.bangkit.balisnap.di

import android.content.Context
import com.bangkit.balisnap.api.ApiConfig
import com.bangkit.balisnap.like.LikeDatabase
import com.bangkit.balisnap.repository.DestinationRepository

object Injection {
    fun provideRepository(context: Context): DestinationRepository {
        val apiService = ApiConfig.getApiService()
        val database = LikeDatabase.getInstance(context)
        val destinationyDao = database.likeDao()
        return DestinationRepository.getInstance(context, apiService, destinationyDao)
    }
}