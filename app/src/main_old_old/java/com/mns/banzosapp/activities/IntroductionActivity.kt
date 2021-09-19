package com.mns.banzosapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*


//The same activity is used for General Information screen as well.
class IntroductionActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        if (intent.extras != null) {
            if (intent.getStringExtra("come_from")?.equals("intro")!!) {
                tv_title.text = "Introduction"
            } else {
                tv_title.text = "General Information"
            }
            iv_back.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
