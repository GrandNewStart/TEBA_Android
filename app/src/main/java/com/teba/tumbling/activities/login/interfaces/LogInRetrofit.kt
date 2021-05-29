package com.teba.tumbling.activities.login.interfaces

import com.teba.tumbling.activities.login.models.LogInResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInRetrofit {
    @POST("/kakao-login")
    fun kakaoLogIn(@Body body: RequestBody): Call<LogInResponse>
}