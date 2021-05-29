package com.teba.tumbling

import android.app.Application
import android.content.SharedPreferences
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, Constants.KAKAO_NATIVE_KEY)
        sharedPreferences = getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE)
    }

    companion object {
        lateinit var sharedPreferences: SharedPreferences
    }

}