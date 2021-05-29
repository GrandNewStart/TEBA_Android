package com.teba.tumbling.activities.main.fragments.donation.fragments.list.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.donation.DonationFragment
import com.teba.tumbling.activities.main.fragments.donation.models.Store

class ListAdapter(val fragment: DonationFragment, val items: ArrayList<Store>): BaseAdapter() {

    override fun getCount(): Int {
        return items.count()
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = fragment.layoutInflater.inflate(R.layout.item_donation_store, parent, false)
        view.findViewById<TextView>(R.id.name).text = items[position].name
        view.findViewById<TextView>(R.id.count).text = "${items[position].count}개의 텀블러가 필요해요!"
        view.findViewById<TextView>(R.id.address).text = items[position].location
        view.findViewById<TextView>(R.id.phone).text = items[position].phone

        view.setOnClickListener {
            fragment.showStoreFragment(items[position].idx)
        }

        return view
    }

}