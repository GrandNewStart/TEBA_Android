package com.teba.tumbling.activities.main.fragments.donation.fragments.list

import android.view.View
import android.widget.ListView
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.donation.DonationFragment
import com.teba.tumbling.activities.main.fragments.donation.fragments.list.adapters.ListAdapter
import com.teba.tumbling.activities.main.fragments.donation.models.StoreDetail
import com.teba.tumbling.customs.BaseFragment

class DonationListFragment(val activity: MainActivity,
                           val fragment: DonationFragment): BaseFragment(R.layout.fragment_donation_list) {

    lateinit var listView: ListView
    lateinit var adapter: ListAdapter

    override fun initView(view: View) {
        listView = view.findViewById(R.id.listView)
        adapter = ListAdapter(fragment, fragment.stores)
        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        fragment.tryGetDonationStores()
    }
}