package com.mns.banzosapp.activities.servicesAndRental.rental

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.rental.BeachGearListAdapter
import com.mns.banzosapp.utils.MiddleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_rental.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class RentalActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rental)

        init()
    }

    private fun init() {
        tv_title.text = "Rentals"
        iv_back.setOnClickListener(this)

        tl_rentals!!.removeAllTabs()

        tl_rentals!!.addTab(tl_rentals!!.newTab().setText("Beach".toUpperCase()))
        tl_rentals!!.addTab(tl_rentals!!.newTab().setText("Baby Gear".toUpperCase()))
        tl_rentals!!.addTab(tl_rentals!!.newTab().setText("Bicycle".toUpperCase()))
        tl_rentals!!.addTab(tl_rentals!!.newTab().setText("Automobile".toUpperCase()))
        tl_rentals!!.addTab(tl_rentals!!.newTab().setText("Motorcycle".toUpperCase()))

        val root: View = tl_rentals.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_beachBeachGearList.setHasFixedSize(true)
        rv_beachBeachGearList.layoutManager = GridLayoutManager(this, 2)
        rv_beachBeachGearList.adapter = BeachGearListAdapter(this, "")
        rv_beachBeachGearList.addItemDecoration(MiddleDividerItemDecoration(this,
                MiddleDividerItemDecoration.ALL))

        tl_rentals.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        ll_beach.visibility = View.VISIBLE
                    }
                    1 -> {
                        ll_beach.visibility = View.GONE
                    }
                    2 -> {
                        ll_beach.visibility = View.GONE
                    }
                    3 -> {
                        ll_beach.visibility = View.GONE
                    }
                    4 -> {
                        ll_beach.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        ll_beachSnorkeling.setOnClickListener(this)
        ll_beachBeachGear.setOnClickListener(this)
        ll_beachSurfBoards.setOnClickListener(this)
        ll_beachSUPs.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.ll_beachSnorkeling -> {
                ll_beachSnorkeling.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_white_top, null)
                ll_beachBeachGear.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_beachSurfBoards.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_beachSUPs.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )

                ll_snorkelling.visibility = View.VISIBLE
                ll_beachGear.visibility = View.GONE
                ll_surfBoards.visibility = View.GONE
                ll_SUPs.visibility = View.GONE
            }
            R.id.ll_beachBeachGear -> {
                ll_beachSnorkeling.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_outline_top, null)
                ll_beachBeachGear.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_white_top,
                    null
                )
                ll_beachSurfBoards.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_beachSUPs.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_snorkelling.visibility = View.GONE
                ll_beachGear.visibility = View.VISIBLE
                ll_surfBoards.visibility = View.GONE
                ll_SUPs.visibility = View.GONE
            }
            R.id.ll_beachSurfBoards -> {
                ll_beachSnorkeling.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_outline_top, null)
                ll_beachBeachGear.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_beachSurfBoards.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_white_top,
                    null
                )
                ll_beachSUPs.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_snorkelling.visibility = View.GONE
                ll_beachGear.visibility = View.GONE
                ll_surfBoards.visibility = View.VISIBLE
                ll_SUPs.visibility = View.GONE
            }
            R.id.ll_beachSUPs -> {
                ll_beachSnorkeling.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_outline_top, null)
                ll_beachBeachGear.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_beachSurfBoards.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                ll_beachSUPs.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_white_top,
                    null
                )
                ll_snorkelling.visibility = View.GONE
                ll_beachGear.visibility = View.GONE
                ll_surfBoards.visibility = View.GONE
                ll_SUPs.visibility = View.VISIBLE
            }
        }
    }
}
