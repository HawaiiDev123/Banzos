package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.mns.banzosapp.R
import com.mns.banzosapp.helper.base.AppBaseActivity

class SplashActivity : AppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        initializeAllView()
    }

    override fun initializeAllView() {
        Handler().postDelayed({
            if(prefs.isLogin()==0)
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }

    override fun setListsAndAdapters() {
    }

    override fun clickListeners() {
    }
}
