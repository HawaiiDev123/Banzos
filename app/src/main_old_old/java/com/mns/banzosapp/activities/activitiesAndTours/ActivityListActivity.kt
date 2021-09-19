package com.mns.banzosapp.activities.activitiesAndTours

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.activitiesAndTours.ActivitiesListAdapter
import kotlinx.android.synthetic.main.activity_list_activity.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ActivityListActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_activity)

        init()
    }

    private fun init() {
        tv_title.text = "Scuba Diving"
        iv_back.setOnClickListener(this)

        tl_activityList!!.removeAllTabs()

        tl_activityList!!.addTab(tl_activityList!!.newTab().setText("All Trips"))
        tl_activityList!!.addTab(tl_activityList!!.newTab().setText("Top Rated"))
        tl_activityList!!.addTab(tl_activityList!!.newTab().setText("Private Trips"))
        tl_activityList!!.addTab(tl_activityList!!.newTab().setText("Showcased"))

        val root: View = tl_activityList.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_activitiesList.setHasFixedSize(true)
        rv_activitiesList.layoutManager = LinearLayoutManager(this)

        rv_activitiesList.adapter =
            ActivitiesListAdapter(
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
