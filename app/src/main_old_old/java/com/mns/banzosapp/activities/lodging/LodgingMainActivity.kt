package com.mns.banzosapp.activities.lodging

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.BeachListAdapter
import kotlinx.android.synthetic.main.activity_lodging.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class LodgingMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lodging)

        init()
    }

    private fun init() {
        tv_title.text = "Lodging"
        iv_back.setOnClickListener(this)

        tl_lodging!!.removeAllTabs()

        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("RESORTS"))
        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("HOTELS"))
        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("BED-BREAKFAST"))
        tl_lodging!!.addTab(tl_lodging!!.newTab().setText("VACATION HOMES"))

        val root: View = tl_lodging.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_lodging.setHasFixedSize(true)
        rv_lodging.layoutManager = GridLayoutManager(this, 3)

        rv_lodging.adapter = BeachListAdapter(this, "lodging")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
