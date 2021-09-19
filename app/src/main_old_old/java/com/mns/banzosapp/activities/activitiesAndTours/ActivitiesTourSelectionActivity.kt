package com.mns.banzosapp.activities.activitiesAndTours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.activitiesAndTours.fishingTrip.TripMainActivity
import kotlinx.android.synthetic.main.activity_activities_tour_selection.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ActivitiesTourSelectionActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities_tour_selection)

        init()
    }

    private fun init() {

        tv_title.text = "Activities And Tours"
        iv_back.setOnClickListener(this)

        btn_activities.setOnClickListener(this)
        btn_fishingTrips.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_activities -> {
                val intent = Intent(this, ActivityMainActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_fishingTrips -> {
                val intent = Intent(this, TripMainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
