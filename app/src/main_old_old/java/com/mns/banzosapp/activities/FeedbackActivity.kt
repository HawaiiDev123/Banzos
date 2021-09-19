package com.mns.banzosapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class FeedbackActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        init()
    }

    private fun init() {
        tv_title.text = "Feedback"
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
