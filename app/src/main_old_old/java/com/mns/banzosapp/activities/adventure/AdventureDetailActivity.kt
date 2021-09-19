package com.mns.banzosapp.activities.adventure

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_adventure_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class AdventureDetailActivity : AppCompatActivity(), View.OnClickListener {

    private var comeFrom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventure_detail)

        init()
    }

    private fun init() {

        if (intent.extras != null) {
            comeFrom = intent.getStringExtra("come_from")
            when (comeFrom) {
                "adventure" -> {
                    tv_title.text = "Adventures"
                    tv_placeName.text = "Kohanaiki Beach Sate Park Camping"
                }
                "poi" -> {
                    tv_title.text = "Point of Interest"
                    tv_placeName.text = "King Kamahameha Monument"
                }
                "other_things"->{
                    tv_title.text = "Skyline Park"
                    tv_placeName.text = "Skyline Park"
                }
            }
        }

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
