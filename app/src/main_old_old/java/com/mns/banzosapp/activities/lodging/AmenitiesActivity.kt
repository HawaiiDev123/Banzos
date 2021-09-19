package com.mns.banzosapp.activities.lodging

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AmenitiesListAdapter
import com.mns.banzosapp.model.AmenitiesModel
import kotlinx.android.synthetic.main.activity_amenities.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class AmenitiesActivity : AppCompatActivity(), View.OnClickListener {

    private var amenitiesModel: AmenitiesModel? = null
    private var amenitiesList: ArrayList<AmenitiesModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amenities)

        init()
    }

    private fun init() {

        amenitiesList = ArrayList()

        tv_title.text = "Amenities"
        iv_back.setOnClickListener(this)

        rv_amenitiesList.setHasFixedSize(true)
        rv_amenitiesList.layoutManager = GridLayoutManager(this, 2)

        populateDummyData()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

    private fun populateDummyData() {

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_wifi_black
        amenitiesModel?.amenitiesName = "Free Internet"
        amenitiesList?.add(amenitiesModel!!)

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_breakfast_black
        amenitiesModel?.amenitiesName = "Breakfast"
        amenitiesList?.add(amenitiesModel!!)

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_car_black
        amenitiesModel?.amenitiesName = "Parking"
        amenitiesList?.add(amenitiesModel!!)

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_swimmer_black
        amenitiesModel?.amenitiesName = "Pool"
        amenitiesList?.add(amenitiesModel!!)

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_dining_black
        amenitiesModel?.amenitiesName = "Dining"
        amenitiesList?.add(amenitiesModel!!)

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_gym_black
        amenitiesModel?.amenitiesName = "Gym"
        amenitiesList?.add(amenitiesModel!!)

        amenitiesModel = AmenitiesModel()
        amenitiesModel?.image = R.drawable.ic_spa_black
        amenitiesModel?.amenitiesName = "Spa"
        amenitiesList?.add(amenitiesModel!!)

        rv_amenitiesList.adapter = AmenitiesListAdapter(this, amenitiesList!!)
    }
}
