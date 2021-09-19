package com.mns.banzosapp.activities.activitiesAndTours.fishingTrip

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.gift.GiveAsGiftActivity
import kotlinx.android.synthetic.main.activity_boat_profile_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class BoatProfileDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boat_profile_detail)

        init()
    }

    private fun init() {

        tv_title.text = "Fishing Boat"
        iv_back.setOnClickListener(this)

        cv_boatInfo.setOnClickListener(this)
        cv_targetedFish.setOnClickListener(this)
        cv_captainBio.setOnClickListener(this)
        cv_deckhandBio.setOnClickListener(this)
        cv_tournament.setOnClickListener(this)
        cv_policiesAndFees.setOnClickListener(this)

        tv_giveAsGift.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_giveAsGift -> {
                val intent = Intent(this, GiveAsGiftActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_boatInfo -> {
                val intent = Intent(this, BoatInformationActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_targetedFish -> {
                val intent = Intent(this, TargetedFishActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_captainBio -> {
                val intent = Intent(this, BoatCaptainBioActivity::class.java)
                intent.putExtra("come_from", "captain")
                startActivity(intent)
            }
            R.id.cv_deckhandBio -> {
                val intent = Intent(this, BoatCaptainBioActivity::class.java)
                intent.putExtra("come_from", "deckhand")
                startActivity(intent)
            }
            R.id.cv_tournament -> {
                val intent = Intent(this, BoatTournamentActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_policiesAndFees -> {
                val intent = Intent(this, BoatPoliciesFeesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
