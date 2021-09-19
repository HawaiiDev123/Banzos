package com.mns.banzosapp.activities.lodging

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.HotelResortsListAdapter
import kotlinx.android.synthetic.main.activity_lodging_list.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class LodgingListActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lodging_list)

        init()
    }

    private fun init() {
        tv_title.text = "Hotels"
        iv_back.setOnClickListener(this)

        tl_lodging!!.removeAllTabs()

        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("NORTH\nKohala n Hamakua"))
        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("SOUTH\nMilolii to Pahala"))
        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("EAST\nHilo n Puna"))
        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("WEST\nKailua-Kona"))

        val root: View = tl_lodging.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_loadgingList.setHasFixedSize(true)
        rv_loadgingList.layoutManager = LinearLayoutManager(this)

        rv_loadgingList.adapter = HotelResortsListAdapter(this, "")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
