package com.teba.tumbling.customs

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.teba.tumbling.Api
import com.teba.tumbling.Constants
import retrofit2.Retrofit

open class BaseActivity(val layoutId: Int): AppCompatActivity(), ActivityInterface {

    private val retrofit = Api.retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initView()
    }

    override fun initView() {}

    fun printLog(message: String?) {
        Log.d(Constants.APP_NAME, "---> ${message?: "NO MESSAGE"}")
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message ?: "", Toast.LENGTH_SHORT).show()
    }

    fun downloadImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }

}

interface ActivityInterface {
    fun initView()
}