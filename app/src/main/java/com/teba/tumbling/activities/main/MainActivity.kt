package com.teba.tumbling.activities.main

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.naver.maps.geometry.LatLng
import com.teba.tumbling.Constants
import com.teba.tumbling.GlobalApplication.Companion.sharedPreferences
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.adapters.PagerAdapter
import com.teba.tumbling.activities.main.fragments.donation.DonationFragment
import com.teba.tumbling.activities.main.fragments.map.MapFragment
import com.teba.tumbling.customs.BaseActivity

class MainActivity: BaseActivity(R.layout.activity_main) {

    private lateinit var mainMenu: Button
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var mapFragment: MapFragment
    private lateinit var donationFragment: DonationFragment
    private lateinit var pageAdapter: PagerAdapter
    var currentPos = LatLng(0.0, 0.0)
    var canGoBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
        initTabs()
    }

    override fun initView() {
        super.initView()
        mainMenu = findViewById(R.id.mainMenu)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        mainMenu.setOnClickListener {
            if (canGoBack) {
                donationFragment.popBack()
            }
        }
    }

    private fun initTabs() {
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tabLayout.selectTab(tabLayout.getTabAt(position))
            if (position == 0) {
                val view = layoutInflater.inflate(R.layout.item_tab, null)
                val textView = view.findViewById<TextView>(R.id.textView)
                textView.text = "둘러보기"
                tab.customView = view
            }
            if (position == 1) {
                val view = layoutInflater.inflate(R.layout.item_tab, null)
                val textView = view.findViewById<TextView>(R.id.textView)
                textView.text = "기부하기"
                textView.setTextColor(Color.GRAY)
                tab.customView = view
            }
        }.attach()
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val textView = tab?.view?.findViewById<TextView>(R.id.textView)
                textView?.setTextColor(Color.BLACK)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val textView = tab?.view?.findViewById<TextView>(R.id.textView)
                textView?.setTextColor(Color.GRAY)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    private fun initViewPager() {
        mapFragment = MapFragment(this)
        donationFragment = DonationFragment(this)
        pageAdapter = PagerAdapter(this, mapFragment, donationFragment)
        viewPager.adapter = pageAdapter
        viewPager.isUserInputEnabled = false
    }

    fun toggleMenuButton(canGoBack: Boolean) {
        this.canGoBack = canGoBack
        mainMenu.foreground = if (canGoBack) resources.getDrawable(R.drawable.icon_back) else resources.getDrawable(R.drawable.icon_menu)
    }

}