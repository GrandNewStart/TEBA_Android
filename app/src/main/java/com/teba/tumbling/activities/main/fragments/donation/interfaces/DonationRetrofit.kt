package com.teba.tumbling.activities.main.fragments.donation.interfaces

import com.teba.tumbling.activities.main.fragments.donation.models.StoreDetailResponse
import com.teba.tumbling.activities.main.fragments.donation.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DonationRetrofit {
    @GET("/donates")
    fun getDonationStores(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double): Call<StoreResponse>

    @GET("/stores/{id}")
    fun getStoreDetail(@Path("id")idx: Int): Call<StoreDetailResponse>
}