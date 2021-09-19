package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.activitiesAndTours.TargetedFishListAdapter
import com.mns.banzosapp.utils.Global
import kotlinx.android.synthetic.main.activity_targeted_fish.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class TargetedFishActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_targeted_fish)

        init()
    }

    private fun init() {

        tv_title.text = "Targeted Fish"
        iv_back.setOnClickListener(this)

        rv_targetedFish.setOnClickListener(this)
        rv_targetedFish.layoutManager=GridLayoutManager(this,3)
        rv_targetedFish.adapter=TargetedFishListAdapter(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

}
