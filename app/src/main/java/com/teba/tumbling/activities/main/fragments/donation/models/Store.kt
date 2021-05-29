package com.teba.tumbling.activities.main.fragments.donation.models

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("idx")
    val idx: Int,
    @SerializedName("storeName")
    val name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("possibleCount")
    val count: Int,
    @SerializedName("phone")
    val phone: String
)