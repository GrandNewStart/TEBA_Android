package com.teba.tumbling.activities.main.fragments.map.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.map.MapFragment
import com.teba.tumbling.activities.main.fragments.map.models.Store

class TrendingDialog(val parent: MainActivity, var items: ArrayList<Store>): BottomSheetDialogFragment() {

    private lateinit var title: TextView
    private lateinit var listView: ListView
    private var adapter = ListViewAdapter(parent, items)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_trending, container, false)
        initView(view)
        return view
    }

    fun initView(view: View) {
        title = view.findViewById(R.id.title)
        listView = view.findViewById(R.id.listView)
        adapter = ListViewAdapter(parent, items)
        listView.adapter = adapter
        isCancelable = false
    }

    fun setAdapter(items: ArrayList<Store>) {
        this.items = items
        adapter.notifyDataSetChanged()
    }

    class ListViewAdapter(val activity: MainActivity, val items: List<Store>): BaseAdapter() {

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
            val view = activity.layoutInflater.inflate(R.layout.item_store, parent, false)
            view.findViewById<TextView>(R.id.name).text = items[position].name
            view.findViewById<TextView>(R.id.address).text = items[position].location
            view.findViewById<TextView>(R.id.points).text = "${items[position].points}"
            Glide.with(activity)
                .load(items[position].image)
                .into(view.findViewById(R.id.image))
            val params = view.layoutParams
            params.height = 100
            view.layoutParams = params
            return view
        }

    }

}