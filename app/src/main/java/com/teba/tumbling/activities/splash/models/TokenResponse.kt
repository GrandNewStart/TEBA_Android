package com.teba.tumbling.activities.splash.models

import com.squareup.moshi.Json

data class TokenResponse (
    @Json(name = "isSuccessful")
    val isSuccessful: Boolean,
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String
)