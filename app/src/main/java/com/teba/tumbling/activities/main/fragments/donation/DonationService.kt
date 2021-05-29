package com.teba.tumbling.activities.main.fragments.donation

import com.teba.tumbling.Api
import com.teba.tumbling.activities.main.fragments.donation.interfaces.DonationRetrofit
import com.teba.tumbling.activities.main.fragments.donation.models.StoreResponse
import com.teba.tumbling.activities.main.fragments.donation.models.StoreDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationService {

    fun getDonationStores(
        lat: Double,
        lng: Double,
        onSuccess:(response: StoreResponse )->Unit,
        onFailure:(message: String)->Unit
    ) {
        Api.retrofit2.create(DonationRetrofit::class.java)
            .getDonationStores(lat, lng)
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


    fun getStoreDetail(
        idx: Int,
        onSuccess: (response: StoreDetailResponse) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        Api.retrofit2.create(DonationRetrofit::class.java)
            .getStoreDetail(idx)
            .enqueue(object : Callback<StoreDetailResponse> {
                override fun onResponse(
                    call: Call<StoreDetailResponse>,
                    response: Response<StoreDetailResponse>
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

                override fun onFailure(call: Call<StoreDetailResponse>, t: Throwable) {
                    onFailure(t.message ?: "NO MESSAGE")
                }

            })
    }


}