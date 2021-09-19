package com.mns.banzosapp.activities.wildlife

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_wildlife.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class WildlifeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wildlife)

        init()
    }

    private fun init() {

        tv_title.text = "Wildlife"
        iv_back.setOnClickListener(this)

        tl_wildlife!!.removeAllTabs()

        tl_wildlife!!.addTab(tl_wildlife!!.newTab().setText("REEF SPECIES"))
        tl_wildlife!!.addTab(tl_wildlife!!.newTab().setText("GAMEFISHING"))
        tl_wildlife!!.addTab(tl_wildlife!!.newTab().setText("BIRDS"))
        tl_wildlife!!.addTab(tl_wildlife!!.newTab().setText("PLANTS"))

        val root: View = tl_wildlife.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        cv_wildlife.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.cv_wildlife -> {
                val intent = Intent(this, WildlifeDetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
