package com.teba.tumbling.activities.login.models

import com.squareup.moshi.Json

data class LogInResponse(
    @Json(name = "isSuccess")
    val isSuccess: Boolean,
    @Json(name = "userIdx")
    val userIdx: Int,
    @Json(name = "jwt")
    val jwt: String
)
