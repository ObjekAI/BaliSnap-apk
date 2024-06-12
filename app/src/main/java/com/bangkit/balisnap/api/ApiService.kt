package com.bangkit.balisnap.api


import com.bangkit.balisnap.response.DestinationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("nearby")
    suspend fun getDestination(
        @Query("latitude") latitude:Double,
        @Query("longitude") longitude:Double,
        @Query("radius") radius:Int
    ) : DestinationResponse

    @GET("search")
    suspend fun getSearchDestination(
        @Query("name") name:String
    ) : DestinationResponse

    @GET("destinations/nearby")
    fun getDetail(
        @Query("image") image:String,
        @Query("name") name:String,
        @Query("description") description:String
    ) : Call<DestinationResponse>




}