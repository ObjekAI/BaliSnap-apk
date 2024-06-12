package com.bangkit.balisnap.response

import com.google.gson.annotations.SerializedName

data class DestinationResponse(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("destinations")
	val destinations: List<DestinationsItem?>? = null
)

data class DestinationsItem(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("distance")
	val distance: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)
