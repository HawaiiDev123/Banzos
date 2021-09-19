package com.mns.banzosapp.activities.beach

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.BeachListAdapter
import kotlinx.android.synthetic.main.activity_beach_list.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class BeachListActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beach_list)

        init()
    }

    private fun init() {

        tv_title.text = "Best Beaches on Big Island"
        iv_back.setOnClickListener(this)

        tl_beachList!!.removeAllTabs()

        tl_beachList!!.addTab(tl_beachList!!.newTab().setText("All Beaches"))
        tl_beachList!!.addTab(tl_beachList!!.newTab().setText("Best Snorkeling Beaches"))
        tl_beachList!!.addTab(tl_beachList!!.newTab().setText("Best Surfing Beaches"))

        val root: View = tl_beachList.getChildAt(0)
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

        rv_beachList.adapter = BeachListAdapter(this, "beach")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
