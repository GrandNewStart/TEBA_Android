package com.teba.tumbling.customs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.teba.tumbling.Constants

open class BaseFragment(val layoutId: Int): Fragment(), FragmentInterface {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(layoutId, container, false)
        initView(view)
        return view
    }

    override fun initView(view: View) {}

    fun printLog(message: String?) {
        Log.d(Constants.APP_NAME, "---> ${message?: "NO MESSAGE"}")
    }

    fun showToast(message: String?) {
        Toast.makeText(activity, message ?: "", Toast.LENGTH_SHORT).show()
    }

    fun downloadImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }
}

interface FragmentInterface {
    fun initView(view: View)
}