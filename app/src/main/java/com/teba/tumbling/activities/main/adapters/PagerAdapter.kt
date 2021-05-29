package com.teba.tumbling.activities.main.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.donation.DonationFragment
import com.teba.tumbling.activities.main.fragments.map.MapFragment

class PagerAdapter(activity: MainActivity,
                   private val map: MapFragment,
                   private val donation: DonationFragment): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) return map
        if (position == 1) return donation
        return Fragment()
    }
}