package com.mns.banzosapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.UpcomingEventsListAdapter
import kotlinx.android.synthetic.main.activity_upcoming_events.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class UpcomingEventsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_events)

        init()
    }

    private fun init() {
        tv_title.text = "Upcoming Events"
        iv_back.setOnClickListener(this)

        rv_upcomingEvents.setHasFixedSize(true)
        rv_upcomingEvents.layoutManager = LinearLayoutManager(this)

        rv_upcomingEvents.adapter = UpcomingEventsListAdapter(this, "")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
