package com.mns.banzosapp.activities.combo

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.MostPopComboListAdapter
import kotlinx.android.synthetic.main.activity_most_popular_combo_list.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MostPopularComboListActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_combo_list)

        init()
    }

    private fun init() {

        tv_title.text = "Combo Most Popular"
        iv_back.setOnClickListener(this)

        tl_mostPopularCombo!!.removeAllTabs()

        tl_mostPopularCombo!!.addTab(tl_mostPopularCombo!!.newTab().setText("2 Trips"))
        tl_mostPopularCombo!!.addTab(tl_mostPopularCombo!!.newTab().setText("3 Trips"))
        tl_mostPopularCombo!!.addTab(tl_mostPopularCombo!!.newTab().setText("4 Trips"))
        tl_mostPopularCombo!!.addTab(tl_mostPopularCombo!!.newTab().setText("SHOWCASE"))

        val root: View = tl_mostPopularCombo.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_mostPopComboList.setHasFixedSize(true)
        rv_mostPopComboList.layoutManager = LinearLayoutManager(this)

        rv_mostPopComboList.adapter=MostPopComboListAdapter(this,"")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
