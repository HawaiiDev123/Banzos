package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class BoatPoliciesFeesActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boat_policies_fees)

        init()
    }

    private fun init() {
        tv_title.text = "Policies And Fees"
        iv_back.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
