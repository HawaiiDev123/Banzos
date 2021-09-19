package com.mns.banzosapp.activities.healthSafety

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.HealthSafetyListAdapter
import kotlinx.android.synthetic.main.activity_health_safety.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class HealthSafetyActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_safety)

        init()
    }

    private fun init() {

        tv_title.text = "Health & Safety"
        iv_back.setOnClickListener(this)

        tl_healthAndSafety!!.removeAllTabs()

        tl_healthAndSafety!!.addTab(
            tl_healthAndSafety!!.newTab().setText("Hospitals".toUpperCase())
        )
        tl_healthAndSafety!!.addTab(
            tl_healthAndSafety!!.newTab().setText("Pharmacies".toUpperCase())
        )
        tl_healthAndSafety!!.addTab(
            tl_healthAndSafety!!.newTab().setText("Fire Stations".toUpperCase())
        )
        tl_healthAndSafety!!.addTab(
            tl_healthAndSafety!!.newTab().setText("Police Stations".toUpperCase())
        )
        tl_healthAndSafety!!.addTab(
            tl_healthAndSafety!!.newTab().setText("Safety Info".toUpperCase())
        )

        val root: View = tl_healthAndSafety.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        ll_main.visibility = View.VISIBLE
        ll_safetyInfo.visibility = View.GONE

        rv_healthAndSafety.setHasFixedSize(true)
        rv_healthAndSafety.layoutManager = LinearLayoutManager(this)

        rv_healthAndSafety.adapter = HealthSafetyListAdapter(this, "")

        tl_healthAndSafety.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        ll_main.visibility = View.VISIBLE
                        ll_safetyInfo.visibility = View.GONE
                    }
                    1 -> {
                        ll_main.visibility = View.VISIBLE
                        ll_safetyInfo.visibility = View.GONE
                    }
                    2 -> {
                        ll_main.visibility = View.VISIBLE
                        ll_safetyInfo.visibility = View.GONE
                    }
                    3 -> {
                        ll_main.visibility = View.VISIBLE
                        ll_safetyInfo.visibility = View.GONE
                    }
                    4 -> {
                        ll_main.visibility = View.GONE
                        ll_safetyInfo.visibility = View.VISIBLE
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
