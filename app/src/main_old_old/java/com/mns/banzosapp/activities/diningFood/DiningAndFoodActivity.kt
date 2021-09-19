package com.mns.banzosapp.activities.diningFood

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.diningAndFood.FarmersMarketListAdapter
import kotlinx.android.synthetic.main.activity_dining_and_food.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class DiningAndFoodActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dining_and_food)

        init()
    }

    private fun init() {
        tv_title.text = "Dining And Food"
        iv_back.setOnClickListener(this)

        tl_diningFood!!.removeAllTabs()

        tl_diningFood!!.addTab(tl_diningFood!!.newTab().setText("Restaurants".toUpperCase()))
        tl_diningFood!!.addTab(tl_diningFood!!.newTab().setText("Grocery Stores".toUpperCase()))
        tl_diningFood!!.addTab(tl_diningFood!!.newTab().setText("Farmers Markets".toUpperCase()))

        val root: View = tl_diningFood.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        tl_diningFood.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        rv_diningFood.adapter = FarmersMarketListAdapter(this@DiningAndFoodActivity, "restaurant")
                    }
                    1 -> {
                        rv_diningFood.adapter = FarmersMarketListAdapter(this@DiningAndFoodActivity, "grocery_store")
                    }
                    2->{
                        rv_diningFood.adapter = FarmersMarketListAdapter(this@DiningAndFoodActivity, "farmers_market")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        rv_diningFood.setHasFixedSize(true)
        rv_diningFood.layoutManager = LinearLayoutManager(this)

        rv_diningFood.adapter = FarmersMarketListAdapter(this, "restaurant")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
