package com.mns.banzosapp.activities.otherThingsToDo

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.adventure.AdventureDetailActivity
import kotlinx.android.synthetic.main.activity_other_things_to_do.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class OtherThingsToDoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_things_to_do)

        init()
    }

    private fun init() {

        tv_title.text = "Other Things To Do"
        iv_back.setOnClickListener(this)

        tl_otherThings!!.removeAllTabs()

        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Bars n Nightlife"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Entertainment"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Free things to do"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Shopping"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Tournaments"))

        val root: View = tl_otherThings.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        cv_otherThings.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.cv_otherThings -> {
                val intent = Intent(this, AdventureDetailActivity::class.java)
                intent.putExtra("come_from", "other_things")
                startActivity(intent)
            }
        }
    }
}
