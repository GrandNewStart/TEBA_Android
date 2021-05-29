package com.teba.tumbling.activities.splash

import android.content.Intent
import android.os.Bundle
import com.teba.tumbling.R
import com.teba.tumbling.activities.login.LogInActivity
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.customs.BaseActivity

class SplashActivity: BaseActivity(R.layout.activity_splash) {

    private val service = SplashService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkToken()
    }

    private fun checkToken() {
        service.validateToken(
            { response ->
                printLog("TOKEN VALIDATED")
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            },
            { message ->
                printLog("TOKEN INVALIDATED")
                Intent(this, LogInActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        )
    }

}