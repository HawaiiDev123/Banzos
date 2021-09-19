package com.mns.banzosapp.activities.citiesTown

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class CitiesTownDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_town_detail)

        init()
    }

    private fun init() {

        tv_title.text = "Waikoloa"
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
