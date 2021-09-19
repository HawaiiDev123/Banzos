package com.mns.banzosapp.activities.activitiesAndTours

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.activities.gift.GiveAsGiftActivity
import com.mns.banzosapp.adapters.OtherActivitiesListAdapter
import kotlinx.android.synthetic.main.activity_detail_activity.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ActivityDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)

        init()
    }

    private fun init() {

        tv_title.text = "Scuba Diving"
        iv_back.setOnClickListener(this)

        rv_otherActivities.setHasFixedSize(true)
        rv_otherActivities.layoutManager = GridLayoutManager(this, 2)
        rv_otherActivities.adapter = OtherActivitiesListAdapter(this)

        cv_scubaDiving.setOnClickListener(this)
        cv_highlights.setOnClickListener(this)
        cv_policyDetail.setOnClickListener(this)
        tv_giveAsGift.setOnClickListener(this)
        tv_addToCart.setOnClickListener(this)
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
            R.id.tv_addToCart -> {
                val intent = Intent(this, AddToCartActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_scubaDiving -> {
                if (ll_scubaDiving.visibility == View.VISIBLE) {
                    ll_scubaDiving.visibility = View.GONE
                    iv_moreScuba.visibility = View.VISIBLE
                    iv_lessScuba.visibility = View.GONE
                } else {
                    ll_scubaDiving.visibility = View.VISIBLE
                    iv_moreScuba.visibility = View.GONE
                    iv_lessScuba.visibility = View.VISIBLE
                }
            }
            R.id.cv_highlights -> {
                if (ll_highlights.visibility == View.VISIBLE) {
                    ll_highlights.visibility = View.GONE
                    iv_moreHighlights.visibility = View.VISIBLE
                    iv_lessHighlights.visibility = View.GONE
                } else {
                    ll_highlights.visibility = View.VISIBLE
                    iv_moreHighlights.visibility = View.GONE
                    iv_lessHighlights.visibility = View.VISIBLE
                }
            }
            R.id.cv_policyDetail -> {
                if (ll_policyDetail.visibility == View.VISIBLE) {
                    ll_policyDetail.visibility = View.GONE
                    iv_morePolicy.visibility = View.VISIBLE
                    iv_lessPolicy.visibility = View.GONE
                } else {
                    ll_policyDetail.visibility = View.VISIBLE
                    iv_morePolicy.visibility = View.GONE
                    iv_lessPolicy.visibility = View.VISIBLE
                }
            }
        }
    }
}
