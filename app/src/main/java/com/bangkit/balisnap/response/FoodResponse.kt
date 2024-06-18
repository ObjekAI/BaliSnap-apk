package com.bangkit.balisnap.response

import com.google.gson.annotations.SerializedName

data class FoodResponse(

    @field:SerializedName("data")
    val data: FoodData
)

data class LocationsItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("link")
    val link: String
)

data class FoodData(

    @field:SerializedName("foods")
    val foods: List<FoodsItem>
)

data class FoodsItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("locations")
    val locations: List<LocationsItem>,

    @field:SerializedName("id")
    val id: String
)
