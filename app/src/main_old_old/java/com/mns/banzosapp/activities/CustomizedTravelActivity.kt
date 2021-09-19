package com.mns.banzosapp.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class CustomizedTravelActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customized_travel)

        init()
    }

    private fun init() {

        tv_title.text = "Customize Travel"
        iv_back.setOnClickListener(this)

        openAddToTripPlannerDialog()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

    private fun openAddToTripPlannerDialog() {
        val addToTripPlannerDialog = Dialog(this)
        addToTripPlannerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addToTripPlannerDialog.setCancelable(true)
        addToTripPlannerDialog.setCanceledOnTouchOutside(true)
        addToTripPlannerDialog.setContentView(R.layout.dialog_customize_travel_popup)
        addToTripPlannerDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        addToTripPlannerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        addToTripPlannerDialog.show()
    }
}
