package com.mns.banzosapp.activities.lodging

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.RoomsListAdapter
import kotlinx.android.synthetic.main.activity_lodging_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class LodgingDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lodging_detail)

        init()
    }

    private fun init() {

        tv_title.text = "Hilton Hotel And Resorts"
        iv_back.setOnClickListener(this)

        tl_lodgingDetail!!.removeAllTabs()

        tl_lodgingDetail!!.addTab(tl_lodgingDetail!!.newTab().setText("About Us".capitalize()))
        tl_lodgingDetail!!.addTab(tl_lodgingDetail!!.newTab().setText("Rooms".capitalize()))
        tl_lodgingDetail!!.addTab(tl_lodgingDetail!!.newTab().setText("Policy n Fees".capitalize()))
        tl_lodgingDetail!!.addTab(tl_lodgingDetail!!.newTab().setText("Reviews".capitalize()))

        val root: View = tl_lodgingDetail.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        ll_aboutUs.visibility = View.VISIBLE
        ll_rooms.visibility = View.GONE

        tl_lodgingDetail.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        ll_aboutUs.visibility = View.VISIBLE
                        ll_rooms.visibility = View.GONE
                    }
                    1 -> {
                        ll_aboutUs.visibility = View.GONE
                        ll_rooms.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        rv_rooms.setHasFixedSize(true)
        rv_rooms.layoutManager = LinearLayoutManager(this)
        rv_rooms.adapter = RoomsListAdapter(this, "")

        ll_aboutUs.setOnClickListener(this)
        ll_rooms.setOnClickListener(this)
        cv_amenities.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.cv_amenities -> {
                val intent = Intent(this, AmenitiesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
