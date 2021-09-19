package com.mns.banzosapp.activities

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.VacationPackageListAdapter
import kotlinx.android.synthetic.main.activity_vacation_package.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class VacationPackageActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacation_package)

        init()
    }

    private fun init() {
        tv_title.text = "Vacation Packages"
        iv_back.setOnClickListener(this)

        tl_vacationPackages!!.removeAllTabs()

        tl_vacationPackages!!.addTab(tl_vacationPackages!!.newTab().setText("All Inclusive"))
        tl_vacationPackages!!.addTab(tl_vacationPackages!!.newTab().setText("Fully Escorted"))
        tl_vacationPackages!!.addTab(tl_vacationPackages!!.newTab().setText("Cruise And Tour"))
        tl_vacationPackages!!.addTab(tl_vacationPackages!!.newTab().setText("Wedding Planning"))
        tl_vacationPackages!!.addTab(tl_vacationPackages!!.newTab().setText("Island Location"))

        val root: View = tl_vacationPackages.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }

        rv_vacationPackagesList.setHasFixedSize(true)
        rv_vacationPackagesList.layoutManager = GridLayoutManager(this, 2)
        rv_vacationPackagesList.adapter = VacationPackageListAdapter(this, "")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
