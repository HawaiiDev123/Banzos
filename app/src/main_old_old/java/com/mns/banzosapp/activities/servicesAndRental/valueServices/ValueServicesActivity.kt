package com.mns.banzosapp.activities.servicesAndRental.valueServices

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.adapters.valueServices.LEIGreetingsListAdapter
import kotlinx.android.synthetic.main.activity_value_services.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ValueServicesActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_services)

        init()
    }

    private fun init() {
        tv_title.text = "Value Services"
        iv_back.setOnClickListener(this)

        tl_valueServices!!.removeAllTabs()

        tl_valueServices!!.addTab(tl_valueServices!!.newTab().setText("LEI Greeting".capitalize()))
        tl_valueServices!!.addTab(
            tl_valueServices!!.newTab().setText("Media Solutions".capitalize())
        )
        tl_valueServices!!.addTab(tl_valueServices!!.newTab().setText("Food Delivery".capitalize()))
        tl_valueServices!!.addTab(tl_valueServices!!.newTab().setText("Car Services".capitalize()))
        tl_valueServices!!.addTab(tl_valueServices!!.newTab().setText("Fish Mounting".capitalize()))

        val root: View = tl_valueServices.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        ll_leiGreetings.visibility = View.VISIBLE
        ll_mediaSolutions.visibility = View.GONE
        ll_foodDelivery.visibility = View.GONE
        ll_carServices.visibility = View.GONE
        ll_fishMounting.visibility = View.GONE

        tl_valueServices.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        ll_leiGreetings.visibility = View.VISIBLE
                        ll_mediaSolutions.visibility = View.GONE
                        ll_foodDelivery.visibility = View.GONE
                        ll_carServices.visibility = View.GONE
                        ll_fishMounting.visibility = View.GONE
                    }
                    1 -> {
                        ll_leiGreetings.visibility = View.GONE
                        ll_mediaSolutions.visibility = View.VISIBLE
                        ll_foodDelivery.visibility = View.GONE
                        ll_carServices.visibility = View.GONE
                        ll_fishMounting.visibility = View.GONE
                    }
                    2 -> {
                        ll_leiGreetings.visibility = View.GONE
                        ll_mediaSolutions.visibility = View.GONE
                        ll_foodDelivery.visibility = View.VISIBLE
                        ll_carServices.visibility = View.GONE
                        ll_fishMounting.visibility = View.GONE
                    }
                    3 -> {
                        ll_leiGreetings.visibility = View.GONE
                        ll_mediaSolutions.visibility = View.GONE
                        ll_foodDelivery.visibility = View.GONE
                        ll_carServices.visibility = View.VISIBLE
                        ll_fishMounting.visibility = View.GONE
                    }
                    4 -> {
                        ll_leiGreetings.visibility = View.GONE
                        ll_mediaSolutions.visibility = View.GONE
                        ll_foodDelivery.visibility = View.GONE
                        ll_carServices.visibility = View.GONE
                        ll_fishMounting.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        rv_valueServices.setHasFixedSize(true)
        rv_valueServices.layoutManager = GridLayoutManager(this, 2)
        rv_valueServices.adapter = LEIGreetingsListAdapter(this, "")

        ll_droneCameraMan.visibility = View.VISIBLE
        ll_goProCamRentals.visibility = View.GONE
        ll_picThrive.visibility = View.GONE
        ll_eventFilming.visibility = View.GONE

        btn_orderYourFishMount.setOnClickListener(this)
        btn_getAQuote.setOnClickListener(this)
        tv_droneCameraMan.setOnClickListener(this)
        tv_goProRentals.setOnClickListener(this)
        tv_picThriveMedia.setOnClickListener(this)
        tv_eventFilming.setOnClickListener(this)

        tv_addToCart.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_addToCart->{
                val intent = Intent(this, AddToCartActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_orderYourFishMount -> {
                val intent = Intent(this, FishMountOrderFormActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_droneCameraMan -> {
                tv_droneCameraMan.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_white_top, null)
                tv_goProRentals.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                tv_picThriveMedia.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                tv_eventFilming.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )

                ll_droneCameraMan.visibility = View.VISIBLE
                ll_goProCamRentals.visibility = View.GONE
                ll_picThrive.visibility = View.GONE
                ll_eventFilming.visibility = View.GONE
            }
            R.id.tv_goProRentals -> {
                tv_droneCameraMan.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                tv_goProRentals.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_white_top, null)
                tv_picThriveMedia.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                tv_eventFilming.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )

                ll_droneCameraMan.visibility = View.GONE
                ll_goProCamRentals.visibility = View.VISIBLE
                ll_picThrive.visibility = View.GONE
                ll_eventFilming.visibility = View.GONE
            }
            R.id.tv_picThriveMedia -> {
                tv_droneCameraMan.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.rectangle_bg_outline_top, null)
                tv_goProRentals.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.rectangle_bg_outline_top,
                    null
                )
                tv_picThriveMedia.background =
                    ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_white_top, null)
                tv_eventFilming.background = ResourcesCompat.getDrawable(resources,
                    R.drawable.rectangle_bg_outline_top, null)

                ll_droneCameraMan.visibility = View.GONE
                ll_goProCamRentals.visibility = View.GONE
                ll_picThrive.visibility = View.VISIBLE
                ll_eventFilming.visibility = View.GONE
            }
            R.id.tv_eventFilming -> {
                tv_droneCameraMan.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.rectangle_bg_outline_top, null)
                tv_goProRentals.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.rectangle_bg_outline_top, null)
                tv_picThriveMedia.background = ResourcesCompat.getDrawable(
                    resources, R.drawable.rectangle_bg_outline_top, null)
                tv_eventFilming.background = ResourcesCompat.getDrawable(resources, R.drawable.rectangle_bg_white_top, null)

                ll_droneCameraMan.visibility = View.GONE
                ll_goProCamRentals.visibility = View.GONE
                ll_picThrive.visibility = View.GONE
                ll_eventFilming.visibility = View.VISIBLE
            }
            R.id.btn_getAQuote -> {

            }
        }
    }

}
