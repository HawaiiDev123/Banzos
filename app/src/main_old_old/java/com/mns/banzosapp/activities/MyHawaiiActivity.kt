package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_my_hawaii.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MyHawaiiActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_hawaii)

        init()
    }

    private fun init() {
        tv_title.text = "My Hawaii"
        iv_back.setOnClickListener(this)

        tv_myAccount.setOnClickListener(this)
        tv_feedback.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_myAccount -> {
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_feedback -> {
                val intent = Intent(this, FeedbackActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
