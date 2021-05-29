package com.teba.tumbling.activities.splash.interfaces

import com.teba.tumbling.activities.splash.models.TokenResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SplashRetrofit {
    @GET("/auto-login")
    fun validateToken(@Header("x-access-token") token: String): Call<TokenResponse>
}