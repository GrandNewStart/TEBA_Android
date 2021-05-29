package com.teba.tumbling.activities.main.fragments.donation

import android.view.View
import android.widget.FrameLayout

import android.widget.ListView
import com.teba.tumbling.Constants
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.donation.fragments.list.DonationListFragment
import com.teba.tumbling.activities.main.fragments.donation.fragments.list.adapters.ListAdapter
import com.teba.tumbling.activities.main.fragments.donation.fragments.store.DonationStoreFragment
import com.teba.tumbling.activities.main.fragments.donation.models.Store
import com.teba.tumbling.activities.main.fragments.donation.models.StoreDetail
import com.teba.tumbling.customs.BaseFragment

class DonationFragment(val activity: MainActivity): BaseFragment(R.layout.fragment_donation) {

    private lateinit var container: FrameLayout
    var stores = ArrayList<Store>()
    private var service = DonationService()

    private lateinit var listFragment: DonationListFragment
    private lateinit var storeFragment: DonationStoreFragment

    override fun initView(view: View) {
        container = view.findViewById(R.id.container)
        initFragments()
        parentFragmentManager.beginTransaction().apply {
            add(R.id.container, listFragment)
            commit()
        }
    }

    fun initFragments() {
        listFragment = DonationListFragment(activity, this)
        storeFragment = DonationStoreFragment(activity, this)
    }

    fun showStoreFragment(idx: Int) {
        tryGetStoreDetail(idx)
    }

    fun popBack() {
        parentFragmentManager.beginTransaction().apply {
            remove(storeFragment)
            activity.toggleMenuButton(false)
            commit()
        }
    }

    fun tryGetDonationStores() {
        service.getDonationStores(
            activity.currentPos.latitude,
            activity.currentPos.longitude,
            { response ->
                stores = ArrayList()
                stores = response.result
                listFragment.adapter = ListAdapter(this, stores)
                listFragment.listView.adapter = listFragment.adapter
            },
            { message ->
                showToast(Constants.requestFailed)
            })
    }

    fun tryGetStoreDetail(idx: Int) {
        service.getStoreDetail(
            idx,
            { response ->
                parentFragmentManager.beginTransaction().apply {
                    add(R.id.container, storeFragment)
                    storeFragment.setView(response.result)
                    commit()
                }
                activity.toggleMenuButton(true)
            },
            { message ->
                showToast(Constants.requestFailed)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        activity.toggleMenuButton(storeFragment.isVisible)
    }

}