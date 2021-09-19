package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.activitiesAndTours.PrivateFishingListAdapter
import kotlinx.android.synthetic.main.activity_private_fishing.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class PrivateFishingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_fishing)

        init()
    }

    private fun init() {
        tv_title.text = "Big Island Fishing"
        iv_back.setOnClickListener(this)

        tl_privateTrips!!.removeAllTabs()

        tl_privateTrips!!.addTab(tl_privateTrips!!.newTab().setText("31-39'"))
        tl_privateTrips!!.addTab(tl_privateTrips!!.newTab().setText("40-49'"))
        tl_privateTrips!!.addTab(tl_privateTrips!!.newTab().setText("50' OR BIGGER"))
        tl_privateTrips!!.addTab(tl_privateTrips!!.newTab().setText("SHOWCASE"))

        val root: View = tl_privateTrips.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_privateTrips.setHasFixedSize(true)
        rv_privateTrips.layoutManager = LinearLayoutManager(this)
        rv_privateTrips.adapter = PrivateFishingListAdapter(this, "")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
