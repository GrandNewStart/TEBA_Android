package com.teba.tumbling.activities.main.fragments.donation.models

import com.google.gson.annotations.SerializedName

data class StoreDetail(
    @SerializedName("idx")
    val idx: Int,
    @SerializedName("storeName")
    val name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("latitude")
    val lat: Double,
    @SerializedName("longitude")
    val lng: Double,
    @SerializedName("tumblerCount")
    val tumblerCount: (Int),
    @SerializedName("openingTime")
    val hours: String,
    @SerializedName("storeUrl")
    val web: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("imgUrl")
    val image: String
)