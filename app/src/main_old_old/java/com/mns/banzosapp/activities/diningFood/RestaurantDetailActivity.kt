package com.mns.banzosapp.activities.diningFood

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.diningAndFood.OtherLocationsListAdapter
import com.mns.banzosapp.adapters.diningAndFood.RestaurantReviewsListAdapter
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class RestaurantDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        init()
    }

    private fun init() {

        tv_title.text = "L & L Hawaiian BBQ"
        iv_back.setOnClickListener(this)

        tl_restaurantDetails!!.removeAllTabs()

        tl_restaurantDetails!!.addTab(
            tl_restaurantDetails!!.newTab().setText("About Us".capitalize()))
        tl_restaurantDetails!!.addTab(tl_restaurantDetails!!.newTab().setText("Menu".capitalize()))
        tl_restaurantDetails!!.addTab(
            tl_restaurantDetails!!.newTab().setText("Reviews".capitalize())
        )

        val root: View = tl_restaurantDetails.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        tl_menu!!.removeAllTabs()
        tl_menu!!.addTab(tl_menu!!.newTab().setText("Breakfast".capitalize()))
        tl_menu!!.addTab(tl_menu!!.newTab().setText("Lunch & Dinner\n(Hawaiian)".capitalize()))
        tl_menu!!.addTab(tl_menu!!.newTab().setText("Lunch & Dinner\n" + "(Chinese)".capitalize()))
        tl_menu!!.addTab(tl_menu!!.newTab().setText("Catering\n" + "(Group Portions)".capitalize()))

        val menuRoot: View = tl_menu.getChildAt(0)
        if (menuRoot is LinearLayout) {
            menuRoot.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            menuRoot.dividerPadding = 10
            menuRoot.dividerDrawable = drawable
        }

        ll_aboutUs.visibility = View.VISIBLE
        ll_menu.visibility = View.GONE
        ll_reviews.visibility = View.GONE

        rv_otherLocations.setHasFixedSize(true)
        rv_otherLocations.layoutManager = GridLayoutManager(this, 1)
        rv_otherLocations.adapter = OtherLocationsListAdapter(this)

        rv_reviewRatings.setHasFixedSize(true)
        rv_reviewRatings.layoutManager = LinearLayoutManager(this)
        rv_reviewRatings.adapter = RestaurantReviewsListAdapter(this)

        tl_restaurantDetails.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        ll_aboutUs.visibility = View.VISIBLE
                        ll_menu.visibility = View.GONE
                        ll_reviews.visibility = View.GONE
                    }
                    1 -> {
                        ll_aboutUs.visibility = View.GONE
                        ll_menu.visibility = View.VISIBLE
                        ll_reviews.visibility = View.GONE
                    }
                    2 -> {
                        ll_aboutUs.visibility = View.GONE
                        ll_menu.visibility = View.GONE
                        ll_reviews.visibility = View.VISIBLE
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        btn_giveReview.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_giveReview -> {
                openGiveReviewDialog()
            }
        }
    }

    private fun openGiveReviewDialog() {
        val addToTripPlannerDialog = Dialog(this)
        addToTripPlannerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addToTripPlannerDialog.setCancelable(true)
        addToTripPlannerDialog.setCanceledOnTouchOutside(true)
        addToTripPlannerDialog.setContentView(R.layout.dialog_give_review_popup)
        addToTripPlannerDialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        addToTripPlannerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToTripPlannerDialog.show()
    }
}
