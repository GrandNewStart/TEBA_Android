package com.teba.tumbling.activities.main.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.teba.tumbling.Constants
import com.teba.tumbling.R
import com.teba.tumbling.activities.main.MainActivity
import com.teba.tumbling.activities.main.fragments.map.dialogs.StoreInfoDialog
import com.teba.tumbling.activities.main.fragments.map.models.Store
import com.teba.tumbling.customs.BaseFragment

class MapFragment(val activity: MainActivity): BaseFragment(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var bottom: ConstraintLayout
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var points: TextView
    private lateinit var image: ImageView

    private lateinit var locationSource: FusedLocationSource
    private lateinit var map: NaverMap
    private val service = MapService()
    private var stores = ArrayList<Store>()
    private var markers = ArrayList<Marker>()
    private lateinit var selectedStore: Store
    private var currentPos = LatLng(0.0,0.0)
    private var camMoving = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.mapView)
        bottom = view.findViewById(R.id.bottom)
        name = view.findViewById(R.id.name)
        address = view.findViewById(R.id.address)
        points = view.findViewById(R.id.points)
        image = view.findViewById(R.id.image)

        bottom.visibility = View.GONE
        bottom.setOnClickListener {
            tryGetStoreDetail(selectedStore)
        }

        initLocationSource()
        initNaverMap()
        mapView.onCreate(savedInstanceState)
    }

    fun initLocationSource() {
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    fun initNaverMap() {
        mapView.getMapAsync {
            map = it
            map.locationSource = locationSource
            map.uiSettings.isLocationButtonEnabled = true
            map.addOnLocationChangeListener { location ->
                camMoving = true
            }
            map.addOnCameraIdleListener {
                if (camMoving) {
                    val pos = map.contentBounds.center
                    tryGetStores(pos.latitude, pos.longitude)
                    currentPos = LatLng(pos.latitude, pos.longitude)
                    activity.currentPos = currentPos
                }
                camMoving = false
            }
        }
    }

    override fun onMapReady(p0: NaverMap) {
        map = p0
        map.locationSource = locationSource
        map.uiSettings.isLocationButtonEnabled = true
        map.addOnLocationChangeListener { location ->
            tryGetStores(location.latitude, location.longitude)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (locationSource.isActivated) {
                map.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun tryGetStores(lat: Double, lng: Double) {
        service.getStores(lat, lng,
            { response ->
                stores = ArrayList()
                markers = ArrayList()
                stores = response.result
                if (!stores.isEmpty()){
                    for (store in stores) {
                        val marker = Marker()
                        marker.position = LatLng(store.lat, store.lng)
                        marker.map = map
                        marker.captionText = store.name
                        marker.setOnClickListener {
                            selectedStore = store
                            name.text = store.name
                            address.text = store.location
                            points.text = "${store.points}%"
                            downloadImage(store.image, image)
                            bottom.visibility = View.VISIBLE
                            true
                        }
                        markers.add(marker)
                    }
                    selectedStore = stores[0]
                }
            },
            { message ->
                showToast(Constants.requestFailed)
            })
    }

    fun tryGetStoreDetail(store: Store) {
        service.getStoreDetail(store.idx,
            { response ->
                StoreInfoDialog(activity, response.result).show()
            },
            { message ->
                showToast(Constants.requestFailed)
            })
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        activity.toggleMenuButton(false)
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
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
            activity.printLog("HI HI HI")
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