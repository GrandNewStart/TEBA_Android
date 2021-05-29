package com.teba.tumbling.activities.splash

import com.teba.tumbling.Api
import com.teba.tumbling.Constants
import com.teba.tumbling.GlobalApplication.Companion.sharedPreferences
import com.teba.tumbling.activities.splash.interfaces.SplashRetrofit
import com.teba.tumbling.activities.splash.models.TokenResponse
import okhttp3.FormBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashService() {

    val token: String

    init {
        token = sharedPreferences.getString(Constants.TOKEN, "") ?: ""
    }

    fun validateToken(
        onSuccess: (response: TokenResponse) -> Unit,
        onFailure: (message: String) -> Unit) {
        Api.retrofit.create(SplashRetrofit::class.java)
            .validateToken(token)
            .enqueue(object: Callback<TokenResponse> {
                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            onSuccess(body)
                            return
                        }
                        onFailure("EMPTY BODY")
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    onFailure(t.message ?: "NO MESSAGE")
                }

            })

    }

}