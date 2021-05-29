package com.teba.tumbling.activities.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.teba.tumbling.Api
import com.teba.tumbling.Constants
import com.teba.tumbling.Constants.APP_NAME
import com.teba.tumbling.GlobalApplication.Companion.sharedPreferences
import com.teba.tumbling.R
import com.teba.tumbling.activities.login.interfaces.LogInRetrofit
import com.teba.tumbling.activities.splash.SplashActivity
import com.teba.tumbling.customs.BaseActivity

class LogInActivity: BaseActivity(R.layout.activity_log_in) {

    private lateinit var kakaoButton: Button
    private lateinit var kakaoCallback: (token: OAuthToken?, error: Throwable?) -> Unit
    private val service: LogInService = LogInService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKakaoCallback()
    }

    override fun initView() {
        kakaoButton = findViewById(R.id.kakaoButton)
        kakaoButton.setOnClickListener { showLogInOptions() }
    }

    fun initKakaoCallback() {
        kakaoCallback = { token, error ->
            if (error != null) {
                Log.e(APP_NAME, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(APP_NAME, "로그인 성공 ${token.accessToken}")
                service.logIn(token.accessToken,
                    { response ->
                        sharedPreferences.edit().apply {
                            printLog("---> LOG IN SUCCESS: $response.jwt")
                            putString(Constants.TOKEN, response.jwt)
                            apply()
                        }
                        Intent(this, SplashActivity::class.java).apply {
                            startActivity(this)
                            finish()
                        }
                    },
                    { message ->
                        printLog(message)
                        showToast(Constants.requestFailed)
                    })
            }
        }
    }

    fun showLogInOptions() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("로그인된 카카오톡 계정으로 로그인 하시겠습니까?")
            .setCancelable(true)
            .setPositiveButton("예") { _, _ ->
                UserApiClient.instance.loginWithKakaoTalk(this, callback = kakaoCallback)
            }
            .setNegativeButton("아니오") { _, _ ->
                UserApiClient.instance.loginWithKakaoAccount(this, callback = kakaoCallback)
            }
            .create()
        dialog.show()
    }



}