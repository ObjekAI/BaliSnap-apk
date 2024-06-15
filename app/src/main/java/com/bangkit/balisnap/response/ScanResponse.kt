package com.bangkit.balisnap.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("data")
	val data: ScanData? = null
)

data class ScanData(

	@field:SerializedName("scannedPlace")
	val scannedPlace: ScannedPlace? = null,

	@field:SerializedName("otherDestinations")
	val otherDestinations: List<OtherDestinationsItem?>? = null
)

data class ScannedPlace(

	@field:SerializedName("image")
	val image: String? = null,

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

data class OtherDestinationsItem(

	@field:SerializedName("image")
	val image: String? = null,

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
