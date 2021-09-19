package com.mns.banzosapp.activities.pointOfInterest

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.adventure.AdventureDetailActivity
import kotlinx.android.synthetic.main.activity_point_of_interest.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class PointOfInterestActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_of_interest)

        init()
    }

    private fun init() {
        tv_title.text = "Point Of Interest"
        iv_back.setOnClickListener(this)

        tl_direction!!.removeAllTabs()

        tl_direction!!.addTab(tl_direction!!.newTab().setText("NORTH\nKohala n Hamakua"))
        tl_direction!!.addTab(tl_direction!!.newTab().setText("SOUTH\nMilolii to Pahala"))
        tl_direction!!.addTab(tl_direction!!.newTab().setText("EAST\nHilo n Puna"))
        tl_direction!!.addTab(tl_direction!!.newTab().setText("WEST\nKailua-Kona"))

        val root: View = tl_direction.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        cv_pointOfInterest1.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.cv_pointOfInterest1 -> {
                val intent = Intent(this, AdventureDetailActivity::class.java)
                intent.putExtra("come_from", "poi")
                startActivity(intent)
            }
        }
    }
}
