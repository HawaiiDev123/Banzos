package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.activitiesAndTours.SharedFishingListAdapter
import kotlinx.android.synthetic.main.activity_shared_fishing.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SharedFishingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_fishing)

        init()
    }

    private fun init() {
        tv_title.text = "Big Island Fishing"
        iv_back.setOnClickListener(this)

        rv_sharedFishing.setHasFixedSize(true)
        rv_sharedFishing.layoutManager=LinearLayoutManager(this)
        rv_sharedFishing.adapter=
            SharedFishingListAdapter(
                this,
                ""
            )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
