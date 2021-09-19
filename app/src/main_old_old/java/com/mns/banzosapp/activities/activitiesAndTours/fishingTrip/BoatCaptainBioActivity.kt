package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.activitiesAndTours.TournamentResultListAdapter
import kotlinx.android.synthetic.main.activity_boat_captain_bio.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class BoatCaptainBioActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boat_captain_bio)

        init()
    }

    private fun init() {

        if (intent.extras != null) {
            when (intent.getStringExtra("come_from")) {
                "captain" -> {
                    tv_title.text = "Captains Biography"
                    tv_captainDeckhandName.text = "Captain Shawn Palmer"
                    iv_captainDeckhandImage.setImageResource(R.drawable.ic_captain_bio)
                    ll_captainBio.visibility = View.VISIBLE
                    ll_deckhandBio.visibility = View.GONE
                }
                "deckhand" -> {
                    tv_title.text = "Deckhand Biography"
                    tv_captainDeckhandName.text = "KC Pelle"
                    iv_captainDeckhandImage.setImageResource(R.drawable.ic_deckhand_bio)
                    ll_captainBio.visibility = View.GONE
                    ll_deckhandBio.visibility = View.VISIBLE
                }
            }
        }

        iv_back.setOnClickListener(this)

        rv_tournamentResultAndWins.setHasFixedSize(true)
        rv_tournamentResultAndWins.layoutManager=LinearLayoutManager(this)

        rv_tournamentResultAndWins.adapter=TournamentResultListAdapter(this,"")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
