package com.bangkit.balisnap.api


import com.bangkit.balisnap.response.DestinationResponse
import com.bangkit.balisnap.response.FoodResponse
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


    @GET("/foods")
    suspend fun getFoods(

    ) : FoodResponse






}