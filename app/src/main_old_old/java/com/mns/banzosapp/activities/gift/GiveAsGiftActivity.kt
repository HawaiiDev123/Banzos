package com.mns.banzosapp.activities.gift

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class GiveAsGiftActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_as_gift_activity)

        init()
    }

    private fun init() {
        tv_title.text = "Give As Gift"
        iv_back.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
