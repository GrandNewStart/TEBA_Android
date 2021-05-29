package com.teba.tumbling.activities.main.fragments.map

import com.teba.tumbling.Api
import com.teba.tumbling.activities.main.fragments.map.interfaces.MapRetrofit
import com.teba.tumbling.activities.main.fragments.map.models.StoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapService() {

    fun getStores(
        lat: Double,
        lng: Double,
        onSuccess: (response: StoreResponse)->Unit,
        onFailure: (message: String)->Unit) {
        Api.retrofit2.create(MapRetrofit::class.java)
            .getStores(lat.toString(), lng.toString(), 1)
            .enqueue(object : Callback<StoreResponse> {
                override fun onResponse(
                    call: Call<StoreResponse>,
                    response: Response<StoreResponse>
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

                override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                    onFailure(t.message ?: "NO MESSAGE")
                }

            })
    }

}