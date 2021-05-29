package com.teba.tumbling.activities.main.fragments.map.models

import com.google.gson.annotations.SerializedName

data class StoreDetailResponse(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: StoreDetail
)
