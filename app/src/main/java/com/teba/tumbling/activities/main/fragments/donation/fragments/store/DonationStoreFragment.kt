package com.teba.tumbling.activities.main.fragments.donation.fragments.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.donation.DonationFragment
import com.teba.tumbling.activities.main.fragments.donation.models.StoreDetail
import com.teba.tumbling.customs.BaseFragment

class DonationStoreFragment(val activity: MainActivity,
                            val fragment: DonationFragment) : BaseFragment(R.layout.fragment_donation_store){

    private lateinit var mapView: MapView
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var hours: TextView
    private lateinit var web: TextView
    private lateinit var call: TextView
    private lateinit var goButton: Button

    private lateinit var map: NaverMap
    lateinit var storeDetail: StoreDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initView(view: View) {
        mapView = view.findViewById(R.id.mapView)
        name = view.findViewById(R.id.name)
        address = view.findViewById(R.id.address)
        hours = view.findViewById(R.id.hours)
        web = view.findViewById(R.id.web)
        call = view.findViewById(R.id.call)
        goButton = view.findViewById(R.id.goButton)

        goButton.setOnClickListener {

        }

        name.text = storeDetail.name
        address.text = storeDetail.location
        hours.text = storeDetail.hours
        web.text = storeDetail.web
        call.text = storeDetail.phone
//        val pos = LatLng(storeDetail.lat, storeDetail.lng)
//        val marker = Marker(pos)
//        marker.map = map
//        marker.captionText = storeDetail.name
    }

    fun setView(storeDetail: StoreDetail) {
        this.storeDetail = storeDetail
    }

}