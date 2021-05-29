package com.teba.tumbling.activities.main.fragments.donation.models

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: ArrayList<Store>
)