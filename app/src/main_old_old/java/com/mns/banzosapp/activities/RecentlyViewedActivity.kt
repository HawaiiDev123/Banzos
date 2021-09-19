package com.mns.banzosapp.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.FavTripListAdapter
import com.mns.banzosapp.callbacks.AddToTripPlannerCallback
import kotlinx.android.synthetic.main.activity_recently_viewed.*
import kotlinx.android.synthetic.main.dialog_fav_trips_popup.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.tv_title

class RecentlyViewedActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recently_viewed)

        init()
    }

    private fun init() {
        tv_title.text = "Recently Viewed"
        iv_back.setOnClickListener(this)

        rv_recentlyViewed.setHasFixedSize(true)
        rv_recentlyViewed.layoutManager = LinearLayoutManager(this)

        rv_recentlyViewed.adapter = FavTripListAdapter(this, "recently_viewed",
            object : AddToTripPlannerCallback {
                override fun addToTripPlanner() {
                    openAddToTripPlannerDialog()
                }
            })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

    private fun openAddToTripPlannerDialog() {
        val addToTripPlannerDialog = Dialog(this)
        addToTripPlannerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addToTripPlannerDialog.setCancelable(true)
        addToTripPlannerDialog.setCanceledOnTouchOutside(true)
        addToTripPlannerDialog.setContentView(R.layout.dialog_fav_trips_popup)
        addToTripPlannerDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        addToTripPlannerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToTripPlannerDialog.btn_cancel.setOnClickListener {
            addToTripPlannerDialog.dismiss()
        }
        addToTripPlannerDialog.show()
    }
}
