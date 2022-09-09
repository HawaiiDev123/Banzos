package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mns.banzosapp.R
import com.mns.banzosapp.helper.base.AppBaseActivity
import kotlinx.android.synthetic.main.activity_my_hawaii.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MyHawaiiActivity : AppBaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_hawaii)
        initializeAllView()
    }

    override fun initializeAllView() {
        tv_title.text = "My Hawaii"
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        clickListeners()
    }

    override fun clickListeners() {
        iv_back.setOnClickListener(this)
        tv_myAccount.setOnClickListener(this)
        tv_feedback.setOnClickListener(this)
        tvMyHawaiiLogout.setOnClickListener(this)
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
            R.id.tvMyHawaiiLogout -> {
                prefs.clearUser()
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}
