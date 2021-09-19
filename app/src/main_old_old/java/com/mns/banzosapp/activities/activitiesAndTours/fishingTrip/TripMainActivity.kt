package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_trip_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class TripMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_main)

        init()
    }

    private fun init() {

        tv_title.text = "Big Island Fishing Trips"
        iv_back.setOnClickListener(this)
        btn_sharedTrips.setOnClickListener(this)
        btn_privateTrips.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_sharedTrips->{
                val intent= Intent(this,SharedFishingActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_privateTrips->{
                val intent= Intent(this,PrivateFishingActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
