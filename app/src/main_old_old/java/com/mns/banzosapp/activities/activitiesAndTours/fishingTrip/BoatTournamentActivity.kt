package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.activitiesAndTours.BoatTournamentListAdapter
import kotlinx.android.synthetic.main.activity_boat_tournament.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class BoatTournamentActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boat_tournament)

        init()
    }

    private fun init() {
        tv_title.text = "Tournament"
        iv_back.setOnClickListener(this)

        rv_boatTournament.setHasFixedSize(true)
        rv_boatTournament.layoutManager = LinearLayoutManager(this)
        rv_boatTournament.adapter = BoatTournamentListAdapter(this, "")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
