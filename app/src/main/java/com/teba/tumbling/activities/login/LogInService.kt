package com.teba.tumbling.activities.login

import com.teba.tumbling.Api
import com.teba.tumbling.activities.login.interfaces.LogInRetrofit
import com.teba.tumbling.activities.login.models.LogInResponse

import okhttp3.FormBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInService() {

    fun logIn(
        accessToken: String,
        onSuccess: (response: LogInResponse) -> Unit,
        onFailure: (message: String) -> Unit) {
        val body = FormBody.Builder()
            .add("accessToken", accessToken)
            .build()
        Api.retrofit.create(LogInRetrofit::class.java)
            .kakaoLogIn(body)
            .enqueue(object: Callback<LogInResponse> {
                override fun onResponse(
                    call: Call<LogInResponse>,
                    response: Response<LogInResponse>
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

                override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                    onFailure(t.message ?: "NO MESSAGE")
                }
            })
    }

}