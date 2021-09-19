package com.mns.banzosapp.activities.activitiesAndTours

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.BeachListAdapter
import kotlinx.android.synthetic.main.activity_main_activity.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ActivityMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity)

        init()
    }

    private fun init() {

        tv_title.text = "Activities And Tours"
        iv_back.setOnClickListener(this)

        tl_activities!!.removeAllTabs()

        tl_activities!!.addTab(tl_activities!!.newTab().setText("Ocean Activity"))
        tl_activities!!.addTab(tl_activities!!.newTab().setText("Island Activity"))

        val root: View = tl_activities.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_beachList.setHasFixedSize(true)
        rv_beachList.layoutManager = GridLayoutManager(this, 3)

        rv_beachList.adapter = BeachListAdapter(this,"activities")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
