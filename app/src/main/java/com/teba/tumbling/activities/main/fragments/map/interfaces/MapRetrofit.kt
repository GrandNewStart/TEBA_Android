package com.teba.tumbling.activities.main.fragments.map.interfaces

import com.teba.tumbling.activities.main.fragments.map.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MapRetrofit {
    @GET("/stores")
    fun getStores(@Query("latitude") latitude: String,
                    @Query("longitude") longitude: String,
                    @Query("distance") distance: Int): Call<StoreResponse>
}