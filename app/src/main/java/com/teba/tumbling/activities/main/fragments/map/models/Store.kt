package com.teba.tumbling.activities.main.fragments.map.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Store(
    @SerializedName("idx")
    val idx: Int,
    @SerializedName("tumblerCount")
    val tumblerCount: Int,
    @SerializedName("latitude")
    val lat: Double,
    @SerializedName("longitude")
    val lng: Double,
    @SerializedName("storeName")
    val name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("tumblingPoint")
    val points: Int,
    @SerializedName("imgUrl")
    val image: String
)