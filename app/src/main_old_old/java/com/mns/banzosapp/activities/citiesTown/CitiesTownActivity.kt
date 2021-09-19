package com.mns.banzosapp.activities.citiesTown

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_cities_town.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class CitiesTownActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_town)

        init()
    }

    private fun init() {

        tv_title.text = "Cities & Towns"
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

        cv_citiesTown1.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.cv_citiesTown1 -> {
                val intent = Intent(this, CitiesTownDetailActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_citiesTown2 -> {
                val intent = Intent(this, CitiesTownDetailActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_citiesTown3 -> {
                val intent = Intent(this, CitiesTownDetailActivity::class.java)
                startActivity(intent)
            }
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
