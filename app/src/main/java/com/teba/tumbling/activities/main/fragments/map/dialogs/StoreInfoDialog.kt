package com.teba.tumbling.activities.main.fragments.map.dialogs

import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.map.models.Store
import com.teba.tumbling.activities.main.fragments.map.models.StoreDetail

class StoreInfoDialog(val activity: MainActivity, val store: StoreDetail) {

    fun show() {
        val dialog = DialogPlus.newDialog(activity)
            .setContentHolder(ViewHolder(R.layout.dialog_store_info))
            .setExpanded(false)
            .setCancelable(true)
            .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            .setPadding(0,0,0,50)
            .create()

        dialog.holderView.findViewById<Button>(R.id.downButton).setOnClickListener {
            dialog.dismiss()
        }
        dialog.holderView.findViewById<TextView>(R.id.name).text = store.name
        dialog.holderView.findViewById<TextView>(R.id.remains).text = "잔여 텀블러 ${store.tumblerCount}개"
        dialog.holderView.findViewById<TextView>(R.id.address).text = store.location
        dialog.holderView.findViewById<TextView>(R.id.web).text = store.web
        dialog.holderView.findViewById<TextView>(R.id.call).text = store.phone
        Glide.with(activity)
            .load(store.image)
            .into(dialog.holderView.findViewById(R.id.image))

        dialog.show()
    }

}