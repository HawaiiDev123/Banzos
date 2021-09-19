package com.mns.banzosapp.activities.adventure

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_adventure.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class AdventureActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventure)

        init()
    }

    private fun init() {

        tv_title.text = "Adventure"
        iv_back.setOnClickListener(this)

        tl_adventure!!.removeAllTabs()

        tl_adventure!!.addTab(tl_adventure!!.newTab().setText("Camping"))
        tl_adventure!!.addTab(tl_adventure!!.newTab().setText("Hiking"))
        tl_adventure!!.addTab(tl_adventure!!.newTab().setText("Waterfalls"))
        tl_adventure!!.addTab(tl_adventure!!.newTab().setText("Volcano"))
        tl_adventure!!.addTab(tl_adventure!!.newTab().setText("Other"))

        val root: View = tl_adventure.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        cv_adventure.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.cv_adventure -> {
                val intent = Intent(this, AdventureDetailActivity::class.java)
                intent.putExtra("come_from", "adventure")
                startActivity(intent)
            }
        }
    }
}
